package id.uff.lucasmartello20241.devwebapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.uff.lucasmartello20241.devwebapi.model.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
    
    @Query(value = "select * from tbcartitem where cart_id = :cartId and sanctuarypetid = :sanctuaryPetId", nativeQuery = true)
    public CartItem findByCartIdSanctuaryPetId(@Param("cartId") int cartId, @Param("sanctuaryPetId") int sanctuaryPetId);
}
