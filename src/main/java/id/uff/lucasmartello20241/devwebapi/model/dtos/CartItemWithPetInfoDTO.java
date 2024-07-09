package id.uff.lucasmartello20241.devwebapi.model.dtos;

import id.uff.lucasmartello20241.devwebapi.model.entities.Cart;
import id.uff.lucasmartello20241.devwebapi.model.entities.CartItem;
import id.uff.lucasmartello20241.devwebapi.model.entities.Pet;
import id.uff.lucasmartello20241.devwebapi.model.entities.SanctuaryPet;

public record CartItemWithPetInfoDTO(
    int id,
    int cartId,
    int quantity,
    SanctuaryPetWithPetInfoDTO sanctuaryPetWithPetInfoDTO
) {
    public static CartItemWithPetInfoDTO fromEntity(CartItem cartItem, Cart cart, SanctuaryPet sanctuaryPet, Pet pet){
        return new CartItemWithPetInfoDTO(cartItem.getId(), cart.getId(), cartItem.getQuantity(), SanctuaryPetWithPetInfoDTO.fromEntity(sanctuaryPet, pet));
    }
}
