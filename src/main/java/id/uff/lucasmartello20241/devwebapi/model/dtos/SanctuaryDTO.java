package id.uff.lucasmartello20241.devwebapi.model.dtos;

import id.uff.lucasmartello20241.devwebapi.model.entities.Sanctuary;

public record SanctuaryDTO(
        Integer id,
        String name,
        String country,
        String state,
        String city,
        String address,
        UserSimplifiedDTO owner

) {
    public static SanctuaryDTO fromEntity(Sanctuary sanctuary) {
        return new SanctuaryDTO(sanctuary.getId(), sanctuary.getName(), sanctuary.getCountry(), sanctuary.getState(),
                sanctuary.getCity(), sanctuary.getAddress(), UserSimplifiedDTO.fromEntity(sanctuary.getOwner()));
    }
}
