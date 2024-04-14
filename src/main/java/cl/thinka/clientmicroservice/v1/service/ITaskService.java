package cl.thinka.clientmicroservice.v1.service;

import cl.thinka.clientmicroservice.v1.dto.TaskDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddTaskRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditTaskRequestDTO;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;

import java.util.List;

public interface ITaskService {

    public ThinkaResponse listTasks() throws ThinkaException;
    public ThinkaResponse getTaskById(Long id) throws ThinkaException;
    public ThinkaResponse deleteTaskById(Long id) throws ThinkaException;
    public ThinkaResponse editTask(EditTaskRequestDTO task) throws ThinkaException;
    public ThinkaResponse saveTask(AddTaskRequestDTO task) throws ThinkaException;
}
