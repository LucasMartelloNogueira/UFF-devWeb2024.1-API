package id.uff.lucasmartello20241.devwebapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.uff.lucasmartello20241.devwebapi.exceptions.NotFoundException;
import id.uff.lucasmartello20241.devwebapi.model.dtos.SanctuaryPetWithPetInfoDTO;
import id.uff.lucasmartello20241.devwebapi.model.entities.Pet;
import id.uff.lucasmartello20241.devwebapi.model.entities.SanctuaryPet;
import id.uff.lucasmartello20241.devwebapi.model.utils.PageResult;
import id.uff.lucasmartello20241.devwebapi.repositories.PetRepository;
import id.uff.lucasmartello20241.devwebapi.repositories.SanctuaryPetRepository;

@Service
public class SanctuaryPetService {
    
    private final SanctuaryPetRepository sanctuaryPetRepository;
    private final PetRepository petRepository;


    @Autowired
    public SanctuaryPetService(SanctuaryPetRepository sanctuaryPetRepository, PetRepository petRepository) {
        this.sanctuaryPetRepository = sanctuaryPetRepository;
        this.petRepository = petRepository;
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

    public PageResult<SanctuaryPetWithPetInfoDTO> findAllPaginatedBySanctuary(Pageable pageable, int sanctuaryId) {
        Page<SanctuaryPet> sanctuaryPetPage = sanctuaryPetRepository.findAllPaginatedBySanctuary(pageable, sanctuaryId);

        List<SanctuaryPetWithPetInfoDTO> sanctuaryPetsWithPetInfo = new ArrayList<>();
        sanctuaryPetPage.getContent().forEach((sanctuaryPet) -> {
            Pet pet = petRepository.findById(sanctuaryPet.getId()).get();
            sanctuaryPetsWithPetInfo.add(SanctuaryPetWithPetInfoDTO.fromEntity(sanctuaryPet, pet));
        });

        PageResult<SanctuaryPetWithPetInfoDTO> pageResult = new PageResult<>(
            sanctuaryPetPage.getTotalElements(), 
            sanctuaryPetPage.getTotalPages(), 
            sanctuaryPetPage.getNumber(),
            sanctuaryPetPage.getNumberOfElements(),
            sanctuaryPetsWithPetInfo
        );

        return pageResult;
    }

}
