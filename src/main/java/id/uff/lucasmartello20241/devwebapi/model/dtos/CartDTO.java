package id.uff.lucasmartello20241.devwebapi.model.dtos;

import java.util.ArrayList;
import java.util.List;

import id.uff.lucasmartello20241.devwebapi.model.entities.Cart;

public record CartDTO(
    int id,
    int userId,
    List<CartItemDTO> items
) {
    public static CartDTO fromEntity(Cart cart){

        List<CartItemDTO> itemsDTO = new ArrayList<>();
        cart.getItems().forEach((cartItem) -> itemsDTO.add(CartItemDTO.fromEntity(cartItem)));

        return new CartDTO(
            cart.getId(), 
            cart.getUserId(),
            itemsDTO
        );
    }
}
