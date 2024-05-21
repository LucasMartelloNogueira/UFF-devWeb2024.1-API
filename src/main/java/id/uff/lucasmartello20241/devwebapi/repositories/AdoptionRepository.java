package id.uff.lucasmartello20241.devwebapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.uff.lucasmartello20241.devwebapi.model.entities.Adoption;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Integer> {
    
    @Query(value = "select * from tbadoptions", countQuery = "select count(*) from tbadoptions", nativeQuery = true)
    public Page<Adoption> findAllPaginated(Pageable pageable);
}
