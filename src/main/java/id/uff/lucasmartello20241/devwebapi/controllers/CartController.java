package id.uff.lucasmartello20241.devwebapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.uff.lucasmartello20241.devwebapi.model.dtos.CartWithPetsInfoDTO;
import id.uff.lucasmartello20241.devwebapi.model.dtos.UpdateCartDTO;
import id.uff.lucasmartello20241.devwebapi.services.CartService;

@RestController
@RequestMapping("cart")
public class CartController extends BaseController{
    
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartWithPetsInfoDTO> read(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.read(id));
    }

    @PatchMapping("/addItems")
    public ResponseEntity<CartWithPetsInfoDTO> addItems(@RequestBody UpdateCartDTO updateCartDTO){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addItems(updateCartDTO));
    }

    @PatchMapping("/removeItems")
    public ResponseEntity<CartWithPetsInfoDTO> removeItems(@RequestBody UpdateCartDTO updateCartDTO){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.removeItems(updateCartDTO));
    }
    
    
}
