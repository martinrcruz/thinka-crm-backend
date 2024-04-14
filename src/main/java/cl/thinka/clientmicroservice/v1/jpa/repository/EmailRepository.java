package cl.thinka.clientmicroservice.v1.jpa.repository;

import cl.thinka.clientmicroservice.v1.jpa.entity.Email;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository {

    Email save(Email email);

    boolean deleteById(Long id, Long userId);

    Email getById(Long id);

    List<Email> getAll();

}
