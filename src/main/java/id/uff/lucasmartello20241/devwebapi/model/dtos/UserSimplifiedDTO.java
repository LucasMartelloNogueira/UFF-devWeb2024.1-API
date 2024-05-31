package id.uff.lucasmartello20241.devwebapi.model.dtos;

import id.uff.lucasmartello20241.devwebapi.model.entities.User;

public record UserSimplifiedDTO(
    Integer id,
    String name,
    String email
) {
    public static UserSimplifiedDTO fromEntity(User user) {
        return new UserSimplifiedDTO(user.getId(), user.getName(), user.getEmail());
    }
}
