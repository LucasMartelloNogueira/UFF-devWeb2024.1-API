package id.uff.lucasmartello20241.devwebapi.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tbcartitem")
public class CartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Cart cart;

    @Column(name = "sanctuarypetid")
    private int sanctuaryPetId;
    private int quantity;

    public CartItem(Cart cart, int sanctuaryPetId, int quantity){
        this.cart = cart;
        this.sanctuaryPetId = sanctuaryPetId;
        this.quantity = quantity;
    }
}
