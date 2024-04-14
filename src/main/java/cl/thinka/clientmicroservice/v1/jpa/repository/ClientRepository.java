package cl.thinka.clientmicroservice.v1.jpa.repository;

import cl.thinka.clientmicroservice.v1.jpa.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository {

    Client save(Client client);

    boolean deleteById(Long id, Long userId);

    Client getById(Long id);

    List<Client> getAll();

    boolean saveFromWeb(Client client);

}
