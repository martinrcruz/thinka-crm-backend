package cl.thinka.clientmicroservice.v1.jpa.repository.read;

import cl.thinka.clientmicroservice.v1.jpa.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepositoryRead extends JpaRepository<Client, Long> {

    @Query("select c from Client c where c.id = ?1 and c.status = 1")
    Client getClientById(Long id);

    @Query("select c from Client c where c.status = 1")
    List<Client> findAll();
}
