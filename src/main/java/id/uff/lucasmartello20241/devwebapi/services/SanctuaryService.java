package id.uff.lucasmartello20241.devwebapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.uff.lucasmartello20241.devwebapi.exceptions.NotFoundException;
import id.uff.lucasmartello20241.devwebapi.model.entities.Sanctuary;
import id.uff.lucasmartello20241.devwebapi.repositories.SanctuaryRepository;

@Service
public class SanctuaryService {
    private final SanctuaryRepository sanctuaryRepository;

    @Autowired
    public SanctuaryService(SanctuaryRepository sanctuaryRepository) {
        this.sanctuaryRepository = sanctuaryRepository;
    }

    public Sanctuary create(Sanctuary sanctuary) {
        return sanctuaryRepository.save(sanctuary);
    }

    public Sanctuary read(int id) throws NotFoundException {
        return sanctuaryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("sanctuary of id %d not found", id)));
    }

    public Sanctuary update(Sanctuary sanctuary) throws NotFoundException {
        sanctuaryRepository.findById(sanctuary.getId()).orElseThrow(() -> new NotFoundException(String.format("sanctuary of id %d not found", sanctuary.getId())));
        return sanctuaryRepository.save(sanctuary);
    }

    public void delete(int id) throws NotFoundException {
        sanctuaryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("sanctuary of id %d not found", id)));
        sanctuaryRepository.deleteById(id);
    }

    public Page<Sanctuary> findAllPaginated(Pageable pageable) {
        return sanctuaryRepository.findAllPaginated(pageable);
    }
}
