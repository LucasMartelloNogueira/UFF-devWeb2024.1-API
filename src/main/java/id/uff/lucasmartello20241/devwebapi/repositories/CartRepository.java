package id.uff.lucasmartello20241.devwebapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.uff.lucasmartello20241.devwebapi.model.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
    
}
