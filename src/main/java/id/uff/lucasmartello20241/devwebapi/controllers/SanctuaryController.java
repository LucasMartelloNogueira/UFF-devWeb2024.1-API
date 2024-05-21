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

import id.uff.lucasmartello20241.devwebapi.model.entities.Sanctuary;
import id.uff.lucasmartello20241.devwebapi.model.utils.PageResult;
import id.uff.lucasmartello20241.devwebapi.services.SanctuaryService;

@RestController
@RequestMapping("sanctuaries")
public class SanctuaryController extends BaseController{
    
    private final SanctuaryService sanctuaryService;

    @Autowired
    public SanctuaryController(SanctuaryService sanctuaryService) {
        this.sanctuaryService = sanctuaryService;
    }

    @PostMapping()
    public ResponseEntity<Sanctuary> create(@RequestBody Sanctuary sanctuary) {
        return ResponseEntity.status(HttpStatus.OK).body(sanctuaryService.create(sanctuary));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sanctuary> read(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(sanctuaryService.read(id));
    }

    @PutMapping()
    public ResponseEntity<Sanctuary> update(@RequestBody Sanctuary sanctuary) {
        return ResponseEntity.status(HttpStatus.OK).body(sanctuaryService.update(sanctuary));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        sanctuaryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    public ResponseEntity<PageResult<Sanctuary>> findAllPaginated(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "3") int size) {
        
        PageRequest pageable = PageRequest.of(page, size);
        Page<Sanctuary> pageSanctuary = sanctuaryService.findAllPaginated(pageable);

        List<Sanctuary> sanctuaries = new ArrayList<>();
        pageSanctuary.getContent().forEach((sanctuary) -> sanctuaries.add(sanctuary));

        PageResult<Sanctuary> pageResult = new PageResult<>(
            pageSanctuary.getTotalElements(), 
            pageSanctuary.getTotalPages(), 
            pageSanctuary.getNumber(),
            pageSanctuary.getNumberOfElements(),
            sanctuaries
        );
        
        return ResponseEntity.ok().body(pageResult);

    }
}
