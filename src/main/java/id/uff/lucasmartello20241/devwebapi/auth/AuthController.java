package id.uff.lucasmartello20241.devwebapi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.uff.lucasmartello20241.devwebapi.auth.dto.request.LoginRequest;
import id.uff.lucasmartello20241.devwebapi.auth.dto.request.RegisterRequest;
import id.uff.lucasmartello20241.devwebapi.auth.dto.response.LoginResponse;
import id.uff.lucasmartello20241.devwebapi.config.TokenService;
import id.uff.lucasmartello20241.devwebapi.model.entities.User;
import id.uff.lucasmartello20241.devwebapi.repositories.UserRepository;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var user = this.userRepository.findUserByUsername(request.getUsername());
        
        var token = tokenService.generateToken((User) auth.getPrincipal());
        
        return ResponseEntity.ok(new LoginResponse(token, user.getId(), user.getRole()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest request){
        if (this.userRepository.findByUsername(request.name()) != null){
            return ResponseEntity.badRequest().build();
        }
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        User newUser = new User();
        newUser.setUsername(request.username());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(request.role());
        newUser.setName(request.name());
        newUser.setEmail(request.email());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
