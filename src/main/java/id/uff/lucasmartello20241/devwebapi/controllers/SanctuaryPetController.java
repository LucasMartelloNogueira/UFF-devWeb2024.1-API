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

import id.uff.lucasmartello20241.devwebapi.model.dtos.SanctuaryPetWithPetInfoDTO;
import id.uff.lucasmartello20241.devwebapi.model.dtos.SanctuaryPetsWithPetInfoListDTO;
import id.uff.lucasmartello20241.devwebapi.model.entities.SanctuaryPet;
import id.uff.lucasmartello20241.devwebapi.model.utils.PageResult;
import id.uff.lucasmartello20241.devwebapi.services.SanctuaryPetService;

@RestController
@RequestMapping("sanctuaryPets")
public class SanctuaryPetController extends BaseController{
    
    private final SanctuaryPetService sanctuaryPetService;

    @Autowired
    public SanctuaryPetController(SanctuaryPetService sanctuaryPetService) {
        this.sanctuaryPetService = sanctuaryPetService;
    }

    @PostMapping()
    public ResponseEntity<SanctuaryPet> create(@RequestBody SanctuaryPet sanctuaryPet) {
        return ResponseEntity.status(HttpStatus.OK).body(sanctuaryPetService.create(sanctuaryPet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SanctuaryPet> read(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(sanctuaryPetService.read(id));
    }

    @PutMapping()
    public ResponseEntity<SanctuaryPet> update(@RequestBody SanctuaryPet sanctuaryPet) {
        return ResponseEntity.status(HttpStatus.OK).body(sanctuaryPetService.update(sanctuaryPet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        sanctuaryPetService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/GetAllBySanctuaryId/{sanctuaryId}")
    public ResponseEntity<SanctuaryPetsWithPetInfoListDTO> getAllPetsBySanctuary(@PathVariable("sanctuaryId") int sanctuaryId) {
        return ResponseEntity.status(HttpStatus.OK).body(sanctuaryPetService.getAllPetsBySanctuary(sanctuaryId));
    }

    @GetMapping()
    public ResponseEntity<PageResult<SanctuaryPet>> findAllPaginated(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "3") int size) {
        
        PageRequest pageable = PageRequest.of(page, size);
        Page<SanctuaryPet> pageSanctuaryPet = sanctuaryPetService.findAllPaginated(pageable);

        List<SanctuaryPet> sanctuaryPets = new ArrayList<>();
        pageSanctuaryPet.getContent().forEach((sanctuaryPet) -> sanctuaryPets.add(sanctuaryPet));

        PageResult<SanctuaryPet> pageResult = new PageResult<>(
            pageSanctuaryPet.getTotalElements(), 
            pageSanctuaryPet.getTotalPages(), 
            pageSanctuaryPet.getNumber(),
            pageSanctuaryPet.getNumberOfElements(),
            sanctuaryPets
        );
        
        return ResponseEntity.ok().body(pageResult);

    }

    @GetMapping("/bySanctuary")
    public ResponseEntity<PageResult<SanctuaryPetWithPetInfoDTO>> findAllPaginatedBySanctuary(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "3") int size,
        @RequestParam(value = "sanctuaryId") int sanctuaryId) {
        
        PageRequest pageable = PageRequest.of(page, size);
        PageResult<SanctuaryPetWithPetInfoDTO> pageResult = sanctuaryPetService.findAllPaginatedBySanctuary(pageable, sanctuaryId);
        
        return ResponseEntity.ok().body(pageResult);

    }
}
