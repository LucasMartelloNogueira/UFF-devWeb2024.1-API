package id.uff.lucasmartello20241.devwebapi.model.entities;

import java.time.LocalDateTime;

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
@Table(name = "tbsanctuarypets")
@NoArgsConstructor
public class SanctuaryPet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "petid")
    private Integer petId;
    
    @Column(name = "sanctuaryid")
    private Integer sanctuaryId;
   
    @Column(name = "admissiondate")
    private LocalDateTime admissionDate;
    
    private String status;
    private String observations;

    public SanctuaryPet(
        Integer petId,
        Integer sanctuaryId,
        LocalDateTime admissionDate,
        String status,
        String observations
    ) {
        this.petId = petId;
        this.sanctuaryId = sanctuaryId;
        this.admissionDate = admissionDate;
        this.status = status;
        this.observations = observations;
    }

    public SanctuaryPet(
        Integer petId,
        Integer sanctuaryId,
        String status,
        String observations
    ) {
        this.petId = petId;
        this.sanctuaryId = sanctuaryId;
        this.admissionDate = LocalDateTime.now();
        this.status = status;
        this.observations = observations; 
    }
}
