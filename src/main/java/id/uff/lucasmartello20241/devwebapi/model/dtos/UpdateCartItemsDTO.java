package id.uff.lucasmartello20241.devwebapi.model.dtos;

import java.util.List;

public record UpdateCartItemsDTO(
    int cartId,
    List<UpdateCartItemQuantityDTO> items
) {
    
}
