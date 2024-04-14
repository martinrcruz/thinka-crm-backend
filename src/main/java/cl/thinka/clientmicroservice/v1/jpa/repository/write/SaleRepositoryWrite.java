package cl.thinka.clientmicroservice.v1.jpa.repository.write;

import cl.thinka.clientmicroservice.v1.jpa.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepositoryWrite extends JpaRepository<Sale, Long> {

    @Modifying
    @Query("update Sale s set s.status = 0, s.deletedAt = now(), s.deletedBy = ?2 where s.id = ?1")
    void deleteById(Long id, Long userId);

}
