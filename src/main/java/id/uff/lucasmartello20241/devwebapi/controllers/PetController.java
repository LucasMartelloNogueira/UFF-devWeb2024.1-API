package id.uff.lucasmartello20241.devwebapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.uff.lucasmartello20241.devwebapi.model.entities.Pet;
import id.uff.lucasmartello20241.devwebapi.model.utils.PageResult;
import id.uff.lucasmartello20241.devwebapi.services.PetService;

@RestController
@RequestMapping("pets")
public class PetController extends BaseController{
    
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping()
    public ResponseEntity<Pet> create(@RequestBody Pet pet) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.create(pet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> read(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.read(id));
    }

    @PutMapping()
    public ResponseEntity<Pet> update(@RequestBody Pet pet) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.update(pet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        petService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    public ResponseEntity<PageResult<Pet>> findAllPaginated(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "3") int size) {
        
        PageRequest pageable = PageRequest.of(page, size);
        Page<Pet> pagePets = petService.findAllPaginated(pageable);

        List<Pet> pets = new ArrayList<>();
        pagePets.getContent().forEach((pet) -> pets.add(pet));

        PageResult<Pet> pageResult = new PageResult<>(
            pagePets.getTotalElements(), 
            pagePets.getTotalPages(), 
            pagePets.getNumber(),
            pagePets.getNumberOfElements(),
            pets
        );
        
        return ResponseEntity.ok().body(pageResult);

    }
}
