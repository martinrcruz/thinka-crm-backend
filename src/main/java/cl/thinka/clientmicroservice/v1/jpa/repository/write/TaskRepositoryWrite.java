package cl.thinka.clientmicroservice.v1.jpa.repository.write;

import cl.thinka.clientmicroservice.v1.jpa.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositoryWrite extends JpaRepository<Task, Long> {

    @Modifying
    @Query("update Task t set t.status = 0, t.deletedAt = now(), t.deletedBy = ?2 where t.id = ?1")
    void deleteById(Long id, Long userId);

}
