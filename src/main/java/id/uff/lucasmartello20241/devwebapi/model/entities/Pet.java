package id.uff.lucasmartello20241.devwebapi.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tbpets")
public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String animal;
    private int age;
    private double height;
    private double weight;

    public Pet(String name, String animal, int age, double height, double weight) {
        this.name = name;
        this.animal = animal;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

}
