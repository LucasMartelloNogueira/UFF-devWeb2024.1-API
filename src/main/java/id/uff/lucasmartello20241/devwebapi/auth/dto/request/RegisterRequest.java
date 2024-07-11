package id.uff.lucasmartello20241.devwebapi.auth.dto.request;

import id.uff.lucasmartello20241.devwebapi.model.enums.Role;

public record RegisterRequest(
    String username,
    String password,
    Role role,
    String name,
    String email
) {
}
