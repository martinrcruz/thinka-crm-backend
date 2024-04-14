package cl.thinka.clientmicroservice.v1.jpa.repository.read;

import cl.thinka.clientmicroservice.v1.jpa.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositoryRead extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.id = ?1 and t.status = 1")
    Task getTaskById(Long id);

    @Query("select t from Task t where t.status = 1")
    List<Task> findAll();
}
