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
@Table(name = "tbsanctuaries")
@NoArgsConstructor
public class Sanctuary {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private String country;
    private String state;
    private String city;

    private String address;

    @ManyToOne
    private User owner;

    public Sanctuary(String name, String country, String state, String city, String address, User owner) {
        this.name = name;
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
        this.owner = owner;
    }

}
