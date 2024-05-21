package id.uff.lucasmartello20241.devwebapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.uff.lucasmartello20241.devwebapi.exceptions.NotFoundException;
import id.uff.lucasmartello20241.devwebapi.model.entities.Pet;
import id.uff.lucasmartello20241.devwebapi.repositories.PetRepository;
;

@Service
public class PetService {
    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petsRepository) {
        this.petRepository = petsRepository;
    }

    public Pet create(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet read(int id) throws NotFoundException{
        return petRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("pet of id %d not found", id)));
    }

    public Pet update(Pet pet) throws NotFoundException{
        petRepository.findById(pet.getId()).orElseThrow(() -> new NotFoundException(String.format("pet of id %d not found", pet.getId())));
        return petRepository.save(pet);
    }

    public void delete(int id) throws NotFoundException{
        petRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("pet of id %d not found", id)));
        petRepository.deleteById(id);
    }

    public Page<Pet> findAllPaginated(Pageable pageable) {
        return petRepository.findAllPaginated(pageable);
    }
}
