package id.uff.lucasmartello20241.devwebapi.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "tbsanctuaries")
@NoArgsConstructor
public class Sanctuary {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "ownerid")
    private Integer ownerId;
    private String address;

    public Sanctuary(String name, Integer ownerId, String address) {
        this.name = name;
        this.ownerId = ownerId;
        this.address = address;
    }

}
