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

import id.uff.lucasmartello20241.devwebapi.model.entities.Adoption;
import id.uff.lucasmartello20241.devwebapi.model.utils.PageResult;
import id.uff.lucasmartello20241.devwebapi.services.AdoptionService;

@RestController
@RequestMapping("adoptions")
public class AdoptionController extends BaseController {
    
    private final AdoptionService adoptionService;

    @Autowired
    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @PostMapping()
    public ResponseEntity<Adoption> create(@RequestBody Adoption adoption) {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionService.create(adoption));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adoption> read(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionService.read(id));
    }

    @PutMapping()
    public ResponseEntity<Adoption> update(@RequestBody Adoption adoption) {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionService.update(adoption));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        adoptionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    public ResponseEntity<PageResult<Adoption>> findAllPaginated(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "3") int size) {
        
        PageRequest pageable = PageRequest.of(page, size);
        Page<Adoption> pageAdoption = adoptionService.findAllPaginated(pageable);

        List<Adoption> adoptions = new ArrayList<>();
        pageAdoption.getContent().forEach((adoption) -> adoptions.add(adoption));

        PageResult<Adoption> pageResult = new PageResult<>(
            pageAdoption.getTotalElements(), 
            pageAdoption.getTotalPages(), 
            pageAdoption.getNumber(),
            pageAdoption.getNumberOfElements(),
            adoptions
        );
        
        return ResponseEntity.ok().body(pageResult);

    }
}
