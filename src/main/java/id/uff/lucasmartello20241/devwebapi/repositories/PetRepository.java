package id.uff.lucasmartello20241.devwebapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.uff.lucasmartello20241.devwebapi.model.entities.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    
    @Query(value = "select * from tbpets", countQuery = "select count(*) from tbpets", nativeQuery = true)
    public Page<Pet> findAllPaginated(Pageable pageable);
}
