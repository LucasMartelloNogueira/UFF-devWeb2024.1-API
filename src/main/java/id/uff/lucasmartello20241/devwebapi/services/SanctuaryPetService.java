package id.uff.lucasmartello20241.devwebapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.uff.lucasmartello20241.devwebapi.exceptions.NotFoundException;
import id.uff.lucasmartello20241.devwebapi.model.entities.SanctuaryPet;
import id.uff.lucasmartello20241.devwebapi.repositories.SanctuaryPetRepository;

@Service
public class SanctuaryPetService {
    
    private final SanctuaryPetRepository sanctuaryPetRepository;

    @Autowired
    public SanctuaryPetService(SanctuaryPetRepository sanctuaryPetRepository) {
        this.sanctuaryPetRepository = sanctuaryPetRepository;
    }

    public SanctuaryPet create(SanctuaryPet sanctuaryPet) {
        return sanctuaryPetRepository.save(sanctuaryPet);
    }

    public SanctuaryPet read(int id) throws NotFoundException{
        return sanctuaryPetRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("sanctuaryPet of id %d not found", id))); 
    }

    public SanctuaryPet update(SanctuaryPet sanctuaryPet) throws NotFoundException{
        sanctuaryPetRepository.findById(sanctuaryPet.getId()).orElseThrow(() -> new NotFoundException(String.format("sanctuaryPet of id %d not found", sanctuaryPet.getId()))); 
        return sanctuaryPetRepository.save(sanctuaryPet);
    }

    public void delete(int id) throws NotFoundException{
        sanctuaryPetRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("sanctuaryPet of id %d not found", id))); 
        sanctuaryPetRepository.deleteById(id);
    }

    public Page<SanctuaryPet> findAllPaginated(Pageable pageable) {
        return sanctuaryPetRepository.findAllPaginated(pageable);
    }

    public Page<SanctuaryPet> findAllPaginatedBySanctuary(Pageable pageable, int sanctuaryId) {
        return sanctuaryPetRepository.findAllPaginatedBySanctuary(pageable, sanctuaryId);
    }

}
