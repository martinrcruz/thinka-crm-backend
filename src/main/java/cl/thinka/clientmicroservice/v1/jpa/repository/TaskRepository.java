package cl.thinka.clientmicroservice.v1.jpa.repository;

import cl.thinka.clientmicroservice.v1.jpa.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository {

    Task save(Task project);

    boolean deleteById(Long id, Long userId);

    Task getById(Long id);

    List<Task> getAll();

}
