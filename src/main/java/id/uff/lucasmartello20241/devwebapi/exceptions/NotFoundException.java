package id.uff.lucasmartello20241.devwebapi.exceptions;

import java.util.HashMap;
import java.util.Map;


public class NotFoundException extends RuntimeException {
    
    public NotFoundException(String message){
        super(message);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", this.getMessage());
        return map;
    }
}
