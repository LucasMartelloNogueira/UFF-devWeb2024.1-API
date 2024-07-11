package id.uff.lucasmartello20241.devwebapi.auth;

import id.uff.lucasmartello20241.devwebapi.model.enums.Role;

public record UserRegisterDTO(String username, String password, Role role) {
    
}
