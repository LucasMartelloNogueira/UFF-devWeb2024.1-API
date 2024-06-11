package id.uff.lucasmartello20241.devwebapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.uff.lucasmartello20241.devwebapi.model.entities.SanctuaryPet;

@Repository
public interface SanctuaryPetRepository extends JpaRepository<SanctuaryPet, Integer>{
    
    @Query(value = "select * from tbsanctuarypets", countQuery = "select count(*) from tbsanctuarypets", nativeQuery = true)
    public Page<SanctuaryPet> findAllPaginated(Pageable pageable);

    @Query(value = "select * from tbsanctuarypets where sanctuaryid = :sanctuaryId and status = 'WAITING_ADOPTION'", countQuery = "select count(*) from tbsanctuarypets where :sanctuaryId", nativeQuery = true)
    public Page<SanctuaryPet> findAllPaginatedBySanctuary(Pageable pageable, @Param("sanctuaryId") int sanctuaryId);
}
