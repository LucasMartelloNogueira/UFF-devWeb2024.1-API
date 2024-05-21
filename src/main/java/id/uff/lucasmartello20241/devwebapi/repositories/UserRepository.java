package id.uff.lucasmartello20241.devwebapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.uff.lucasmartello20241.devwebapi.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    @Query(value = "select * from tbusers", countQuery = "select count(*) from tbusers", nativeQuery = true)
    public Page<User> findAllPaginated(Pageable pageable);
}
