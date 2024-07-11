package id.uff.lucasmartello20241.devwebapi.config;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import id.uff.lucasmartello20241.devwebapi.auth.Token;
import id.uff.lucasmartello20241.devwebapi.model.dtos.UserLoginDTO;

import java.time.Duration;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    private final String tokenIssuer = "id.uff.lucasmartello";
    private static final int TOKEN_DURATION_SECONDS = 12 * 60 * 60 * 1000;  // 12h


    public Token generateToken(UserLoginDTO userLoginDTO) 
        throws IllegalArgumentException, JWTCreationException{
            
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        
        String token = JWT.create()
                .withIssuer(tokenIssuer)
                .withSubject(userLoginDTO.username())
                .withExpiresAt(Instant.now().plus(Duration.ofSeconds(TOKEN_DURATION_SECONDS)))
                .sign(algoritmo); 
        
        return new Token(token);
    }

    public String validarToken(String token)
        throws IllegalArgumentException, JWTVerificationException {
        
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo) 
                  .withIssuer(tokenIssuer)  
                  .build()              
                  .verify(token)        
                  .getSubject();        
    }
}
