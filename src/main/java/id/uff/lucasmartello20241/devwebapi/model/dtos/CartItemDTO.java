package id.uff.lucasmartello20241.devwebapi.model.dtos;

import id.uff.lucasmartello20241.devwebapi.model.entities.CartItem;

public record CartItemDTO(
        int id,
        int cartId,
        int sanctuaryPetId,
        int quantity) {

    public static CartItemDTO fromEntity(CartItem cartItem) {
        return new CartItemDTO(
            cartItem.getId(), 
            cartItem.getCart().getId(),
            cartItem.getSanctuaryPetId(),
            cartItem.getQuantity());
    }
}
