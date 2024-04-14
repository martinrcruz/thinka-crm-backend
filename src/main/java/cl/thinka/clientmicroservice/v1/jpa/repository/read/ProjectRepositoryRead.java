package cl.thinka.clientmicroservice.v1.jpa.repository.read;

import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepositoryRead extends JpaRepository<Project, Long> {
    @Query("select p from Project p where p.id = ?1 and p.status = 1")
    Project getProjectById(Long id);

    @Query("select p from Project p where p.status = 1")
    List<Project> findAll();
}
