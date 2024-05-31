package id.uff.lucasmartello20241.devwebapi.model.dtos;

import java.util.ArrayList;
import java.util.List;

import id.uff.lucasmartello20241.devwebapi.model.entities.User;

public record UserDTO(
        Integer id,
        String name,
        String email,
        String password,
        List<SanctuaryDTO> sanctuaries
    ) {
    
    public static UserDTO fromEntity(User user) {

        List<SanctuaryDTO> sanctuaries = new ArrayList<>();
        user.getSanctuaries().forEach((sanctuary) -> {
            sanctuaries.add(SanctuaryDTO.fromEntity(sanctuary));
        });

        return new UserDTO(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                sanctuaries);
    }
}
