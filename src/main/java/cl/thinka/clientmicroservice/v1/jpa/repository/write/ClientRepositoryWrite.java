package cl.thinka.clientmicroservice.v1.jpa.repository.write;

import cl.thinka.clientmicroservice.v1.jpa.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositoryWrite extends JpaRepository<Client, Long> {


    @Modifying
    @Query("update Client c set c.status = 0, c.deletedAt = now(), c.deletedBy = ?2 where c.id = ?1")
    void deleteById(Long id, Long userId);

}
