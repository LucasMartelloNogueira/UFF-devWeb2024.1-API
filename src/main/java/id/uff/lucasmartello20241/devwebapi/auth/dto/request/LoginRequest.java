package id.uff.lucasmartello20241.devwebapi.auth.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private final String username;
    private final String password;
}
