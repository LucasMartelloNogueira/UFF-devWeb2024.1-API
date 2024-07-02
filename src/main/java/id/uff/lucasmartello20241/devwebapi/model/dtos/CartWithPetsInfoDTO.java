package id.uff.lucasmartello20241.devwebapi.model.dtos;

import java.util.List;

public record CartWithPetsInfoDTO(
    int id,
    UserSimplifiedDTO user,
    List<SanctuaryPetWithPetInfoDTO> sanctuaryPets
) {
    
}
