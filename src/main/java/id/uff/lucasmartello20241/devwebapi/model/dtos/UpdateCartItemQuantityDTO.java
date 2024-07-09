package id.uff.lucasmartello20241.devwebapi.model.dtos;

public record UpdateCartItemQuantityDTO(
    int cartItemId,
    int quantity
) {
    
}
