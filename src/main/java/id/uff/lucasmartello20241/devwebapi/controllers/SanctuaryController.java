package id.uff.lucasmartello20241.devwebapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

import id.uff.lucasmartello20241.devwebapi.model.dtos.SanctuaryDTO;
import id.uff.lucasmartello20241.devwebapi.model.entities.Sanctuary;
import id.uff.lucasmartello20241.devwebapi.model.utils.PageResult;
import id.uff.lucasmartello20241.devwebapi.services.SanctuaryService;
import id.uff.lucasmartello20241.devwebapi.model.dtos.SanctuaryWithOwnerNameDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("sanctuaries")
public class SanctuaryController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(SanctuaryController.class);
    
    private final SanctuaryService sanctuaryService;

    @Autowired
    public SanctuaryController(SanctuaryService sanctuaryService) {
        this.sanctuaryService = sanctuaryService;
    }

    @PostMapping()
    public ResponseEntity<Sanctuary> create(@RequestBody Sanctuary sanctuary) {
        return ResponseEntity.status(HttpStatus.OK).body(sanctuaryService.create(sanctuary));
    }

    @PostMapping("/createByOwnerName")
    public ResponseEntity<SanctuaryDTO> createByOwnerName(@RequestBody SanctuaryWithOwnerNameDTO sanctuary) {
        Sanctuary newSanctuary = sanctuaryService.createByOwnerName(sanctuary);
        return ResponseEntity.status(HttpStatus.OK).body(SanctuaryDTO.fromEntity(newSanctuary));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SanctuaryDTO> read(@PathVariable("id") int id) {
        SanctuaryDTO sanctuaryDTO = SanctuaryDTO.fromEntity(sanctuaryService.read(id));
        return ResponseEntity.status(HttpStatus.OK).body(sanctuaryDTO);
    }

    @PutMapping()
    public ResponseEntity<SanctuaryDTO> update(@RequestBody Sanctuary sanctuary) {
        SanctuaryDTO sanctuaryDTO = SanctuaryDTO.fromEntity(sanctuaryService.update(sanctuary));
        return ResponseEntity.status(HttpStatus.OK).body(sanctuaryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        sanctuaryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    public ResponseEntity<PageResult<SanctuaryDTO>> findAllPaginated(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "3") int size) {
        
        PageRequest pageable = PageRequest.of(page, size);
        Page<Sanctuary> pageSanctuary = sanctuaryService.findAllPaginated(pageable);

        List<SanctuaryDTO> sanctuaries = new ArrayList<>();
        pageSanctuary.getContent().forEach((sanctuary) -> sanctuaries.add(SanctuaryDTO.fromEntity(sanctuary)));

        PageResult<SanctuaryDTO> pageResult = new PageResult<>(
            pageSanctuary.getTotalElements(), 
            pageSanctuary.getTotalPages(), 
            pageSanctuary.getNumber(),
            pageSanctuary.getNumberOfElements(),
            sanctuaries
        );
        
        return ResponseEntity.ok().body(pageResult);
    }

    @GetMapping("/filter")
    public ResponseEntity<PageResult<SanctuaryDTO>> findBySearchValuePaginated(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "3") int size,
        @RequestParam(value = "searchValue", defaultValue = " ") String searchValue) {

        logger.info("[LOGGER]searchValue = {}", searchValue);
        
        PageRequest pageable = PageRequest.of(page, size);
        Page<Sanctuary> pageSanctuary = sanctuaryService.findBySearchValuePaginated(searchValue, pageable);

        List<SanctuaryDTO> sanctuaries = new ArrayList<>();
        pageSanctuary.getContent().forEach((sanctuary) -> sanctuaries.add(SanctuaryDTO.fromEntity(sanctuary)));

        PageResult<SanctuaryDTO> pageResult = new PageResult<>(
            pageSanctuary.getTotalElements(), 
            pageSanctuary.getTotalPages(), 
            pageSanctuary.getNumber(),
            pageSanctuary.getNumberOfElements(),
            sanctuaries
        );
        
        return ResponseEntity.ok().body(pageResult);
    }

    @GetMapping("/filterSorted")
    public ResponseEntity<PageResult<SanctuaryDTO>> findBySearchValueSortedPaginated(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "3") int size,
        @RequestParam(value = "searchValue", defaultValue = " ") String searchValue,
        @RequestParam(value = "sortField", defaultValue = "id") String sortField,
        @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection) {
        
        Sort sortOption = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending(); 

        PageRequest pageable = PageRequest.of(page, size, sortOption);
        Page<Sanctuary> pageSanctuary = sanctuaryService.findBySearchValueSortedPaginated(searchValue, pageable);

        List<SanctuaryDTO> sanctuaries = new ArrayList<>();
        pageSanctuary.getContent().forEach((sanctuary) -> sanctuaries.add(SanctuaryDTO.fromEntity(sanctuary)));

        PageResult<SanctuaryDTO> pageResult = new PageResult<>(
            pageSanctuary.getTotalElements(), 
            pageSanctuary.getTotalPages(), 
            pageSanctuary.getNumber(),
            pageSanctuary.getNumberOfElements(),
            sanctuaries
        );
        
        return ResponseEntity.ok().body(pageResult);
    }
}
