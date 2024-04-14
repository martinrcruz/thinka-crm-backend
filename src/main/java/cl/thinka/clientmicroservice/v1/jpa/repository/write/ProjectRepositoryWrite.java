package cl.thinka.clientmicroservice.v1.jpa.repository.write;

import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepositoryWrite extends JpaRepository<Project, Long> {

    @Modifying
    @Query("update Project p set p.status = 0, p.deletedAt = now(), p.deletedBy = ?2 where p.id = ?1")
    void deleteById(Long id, Long userId);

}
