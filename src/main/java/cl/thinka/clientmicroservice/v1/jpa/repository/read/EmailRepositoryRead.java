package cl.thinka.clientmicroservice.v1.jpa.repository.read;

import cl.thinka.clientmicroservice.v1.jpa.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmailRepositoryRead extends JpaRepository<Email, Long> {
    @Query("select e from Email e where e.id = ?1 and e.status = 1")
    Email getEmailById(Long id);

    @Query("select e from Email e where e.status = 1")
    List<Email> findAll();
}
