package cl.thinka.clientmicroservice.v1.jpa.repository.write;

import cl.thinka.clientmicroservice.v1.jpa.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepositoryWrite extends JpaRepository<Email, Long> {

    @Modifying
    @Query("update Email e set e.status = 0, e.deletedAt = now(), e.deletedBy = ?2 where e.id = ?1")
    void deleteById(Long id, Long userId);

}
