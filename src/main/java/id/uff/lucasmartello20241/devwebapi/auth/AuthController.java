package id.uff.lucasmartello20241.devwebapi.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTCreationException;

import id.uff.lucasmartello20241.devwebapi.config.TokenService;
import id.uff.lucasmartello20241.devwebapi.controllers.BaseController;
import id.uff.lucasmartello20241.devwebapi.exceptions.UserAlreadyRegisteredException;
import id.uff.lucasmartello20241.devwebapi.model.dtos.UserLoginDTO;
import id.uff.lucasmartello20241.devwebapi.model.entities.User;
import id.uff.lucasmartello20241.devwebapi.services.UserService;

@RestController
@RequestMapping("auth")
public class AuthController extends BaseController{
    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager; 

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public ResponseEntity<Token> login(@RequestBody UserLoginDTO authenticationRecord) {


        var usernamePassword = new UsernamePasswordAuthenticationToken
                (authenticationRecord.username(), authenticationRecord.password());
        try {
            Authentication authentication = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((UserLoginDTO) authentication.getPrincipal());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        catch(IllegalArgumentException |
              JWTCreationException |
              AuthenticationException e) {

            System.out.println("A classe da exceção: " + e.getClass().getName());
            throw e;
        }
    }

    @PostMapping("register")      
    public ResponseEntity<Map<String, String>> cadastrarUsuario(@RequestBody UserRegisterDTO userRegisterDTO) {

        Optional<User> user = userService.findByUsername(userRegisterDTO.username());

        if (!user.isPresent()) {

            User newUser = new User();
            newUser.setUsername(userRegisterDTO.username());
            newUser.setPassword(passwordEncoder.encode(userRegisterDTO.password()));
            newUser.setRole(userRegisterDTO.role());
            newUser.setAccountNonLocked(true);
            newUser.setCredentialsNonExpired(true);
            newUser.setAccountNonExpired(true);
            newUser.setEnabled(true);

            var savedUser = userService.create(newUser);

            Map<String, String> data = new HashMap<>();
            data.put("message", "user created with success");
            data.put("userId", savedUser.getId().toString());

            return ResponseEntity.status(HttpStatus.OK).body(data);
        }
        else {
            throw new UserAlreadyRegisteredException(
                    "Usuário " + userRegisterDTO.username() + " já cadastrado.");
        }
    }
}
