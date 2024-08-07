package id.uff.lucasmartello20241.devwebapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.uff.lucasmartello20241.devwebapi.model.entities.Sanctuary;

@Repository
public interface SanctuaryRepository extends JpaRepository<Sanctuary, Integer>{
    
    @Query(value = "Select * from tbsanctuaries", countQuery = "select count(*) from tbsanctuaries", nativeQuery = true)
    Page<Sanctuary> findAllPaginated(Pageable pageable);  

    @Query(
        value = "select * from tbsanctuaries where (name regexp :searchValue) or (country regexp :searchValue) or (state regexp :searchValue) or (city regexp :searchValue)",
        countQuery = "select count(*) from tbsanctuaries where (name regexp :searchValue) or (country regexp :searchValue) or (state regexp :searchValue) or (city regexp :searchValue)",
        nativeQuery = true)
    Page<Sanctuary> findBySearchValuePaginated(@Param("searchValue") String searchValue, Pageable pageable);

    @Query(
        value = "select s.*, u.name as owner from tbsanctuaries as s join tbusers as u on s.owner_id = u.id where (s.name regexp :searchValue) or (s.country regexp :searchValue) or (s.state regexp :searchValue) or (s.city regexp :searchValue)",
        countQuery = "select  count(*) from (select s.*, u.name as owner from tbsanctuaries as s join tbusers as u on s.owner_id = u.id where (s.name regexp :searchValue) or (s.country regexp :searchValue) or (s.state regexp :searchValue) or (s.city regexp :searchValue)) as su",
        nativeQuery = true)
    Page<Sanctuary> findBySearchValueSortedPaginated(@Param("searchValue") String searchValue, Pageable pageable);
}
