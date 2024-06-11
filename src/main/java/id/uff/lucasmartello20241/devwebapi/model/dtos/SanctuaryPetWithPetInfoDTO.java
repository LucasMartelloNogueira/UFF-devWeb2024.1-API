package id.uff.lucasmartello20241.devwebapi.model.dtos;

import java.time.LocalDateTime;

import id.uff.lucasmartello20241.devwebapi.model.entities.Pet;
import id.uff.lucasmartello20241.devwebapi.model.entities.SanctuaryPet;
import id.uff.lucasmartello20241.devwebapi.model.enums.AdoptionStatus;

public record SanctuaryPetWithPetInfoDTO(
        Integer id,
        Pet pet,
        Integer sanctuaryId,
        LocalDateTime admissionDate,
        AdoptionStatus adoptionStatus,
        String observations) {
    public static SanctuaryPetWithPetInfoDTO fromEntity(SanctuaryPet sanctuaryPet, Pet pet) {
        return new SanctuaryPetWithPetInfoDTO(sanctuaryPet.getId(), pet, sanctuaryPet.getSanctuaryId(),
                sanctuaryPet.getAdmissionDate(), sanctuaryPet.getStatus(), sanctuaryPet.getObservations());
    }
}
