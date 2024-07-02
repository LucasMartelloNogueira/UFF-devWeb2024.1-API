package id.uff.lucasmartello20241.devwebapi.model.dtos;

import java.util.List;

public record UpdateCartDTO(
    int cartId,
    List<Integer> sanctuaryPetsId
) {
    
}
