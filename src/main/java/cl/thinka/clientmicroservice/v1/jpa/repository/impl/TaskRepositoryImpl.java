package cl.thinka.clientmicroservice.v1.jpa.repository.impl;

import cl.thinka.clientmicroservice.v1.jpa.entity.Task;
import cl.thinka.clientmicroservice.v1.jpa.repository.TaskRepository;
import cl.thinka.clientmicroservice.v1.jpa.repository.read.TaskRepositoryRead;
import cl.thinka.clientmicroservice.v1.jpa.repository.write.TaskRepositoryWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    @Autowired
    TaskRepositoryRead taskRepositoryRead;
    @Autowired
    TaskRepositoryWrite taskRepositoryWrite;

    @Override
    public Task save(Task task) {
        return taskRepositoryWrite.save(task);
    }

    @Override
    public boolean deleteById(Long id, Long userId) {
        taskRepositoryWrite.deleteById(id, userId);
        return true;
    }

    @Override
    public Task getById(Long id) {
        return taskRepositoryRead.getTaskById(id);
    }

    @Override
    public List<Task> getAll() {
        return taskRepositoryRead.findAll();
    }
}
