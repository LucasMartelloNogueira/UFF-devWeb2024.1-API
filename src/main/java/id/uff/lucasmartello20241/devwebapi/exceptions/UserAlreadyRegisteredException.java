package id.uff.lucasmartello20241.devwebapi.exceptions;

import java.util.HashMap;
import java.util.Map;

public class UserAlreadyRegisteredException extends RuntimeException{
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", this.getMessage());
        return map;
    }
}
