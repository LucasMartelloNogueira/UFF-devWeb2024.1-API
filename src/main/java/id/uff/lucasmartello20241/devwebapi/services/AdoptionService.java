package id.uff.lucasmartello20241.devwebapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.uff.lucasmartello20241.devwebapi.exceptions.NotFoundException;
import id.uff.lucasmartello20241.devwebapi.model.entities.Adoption;
import id.uff.lucasmartello20241.devwebapi.repositories.AdoptionRepository;

@Service
public class AdoptionService {

    private final AdoptionRepository adoptionRepository;

    @Autowired
    public AdoptionService(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
    }

    public Adoption create(Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    public Adoption read(int id) throws NotFoundException {
        return adoptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("adoption of id %d not found", id)));
    }

    public Adoption update(Adoption adoption) throws NotFoundException {
        adoptionRepository.findById(adoption.getId())
                .orElseThrow(() -> new NotFoundException(String.format("adoption of id %d not found", adoption.getId())));
        return adoptionRepository.save(adoption);
    }

    public void delete(int id) throws NotFoundException {
        adoptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("adoption of id %d not found", id)));
        adoptionRepository.deleteById(id);
    }

    public Page<Adoption> findAllPaginated(Pageable pageable) {
        return adoptionRepository.findAllPaginated(pageable);
    }
}
