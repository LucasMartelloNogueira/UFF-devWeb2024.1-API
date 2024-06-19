package id.uff.lucasmartello20241.devwebapi.model.dtos;

public record SanctuaryWithOwnerNameDTO(
    String name,
    String country,
    String state,
    String city,
    String address,
    String ownerName
) {
    
}
