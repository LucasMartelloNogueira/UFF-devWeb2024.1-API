package id.uff.lucasmartello20241.devwebapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.uff.lucasmartello20241.devwebapi.model.entities.Sanctuary;

@Repository
public interface SanctuaryRepository extends JpaRepository<Sanctuary, Integer>{
    
    @Query(value = "Select * from tbsanctuaries", countQuery = "select count(*) from tbsanctuaries", nativeQuery = true)
    Page<Sanctuary> findAllPaginated(Pageable pageable);  
}
