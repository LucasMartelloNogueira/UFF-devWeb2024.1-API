package id.uff.lucasmartello20241.devwebapi.model.dtos;

import java.util.List;

public record SanctuaryPetsWithPetInfoListDTO(
    List<SanctuaryPetWithPetInfoDTO> sanctuaryPets
) {
    
}
