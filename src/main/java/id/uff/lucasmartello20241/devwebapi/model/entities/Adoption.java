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
@Table(name = "tbadoptions")
@NoArgsConstructor
public class Adoption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "userid")
    private Integer userId;
    
    @Column(name = "sanctuarypetid")
    private Integer sanctuaryPetId;
    
    @Column(name = "datetime")
    private LocalDateTime dateTime;

    public Adoption(Integer userId, Integer sanctuaryPetId, LocalDateTime dateTime) {
        this.userId = userId;
        this.sanctuaryPetId = sanctuaryPetId;
        this.dateTime = dateTime;
    }
}
