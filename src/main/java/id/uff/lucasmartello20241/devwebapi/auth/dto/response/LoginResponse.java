package id.uff.lucasmartello20241.devwebapi.auth.dto.response;

import id.uff.lucasmartello20241.devwebapi.model.enums.Role;
import lombok.Data;


@Data
public class LoginResponse {
    
    private String token;
    private int id;
    private Role role;

    public LoginResponse(String token, int id, Role role){
        this.token = token;
        this.id = id;
        this.role = role;
    }
}
