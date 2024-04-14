package cl.thinka.clientmicroservice.v1.service.impl;

import cl.thinka.clientmicroservice.v1.dto.TaskDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddTaskRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditTaskRequestDTO;
import cl.thinka.clientmicroservice.v1.enums.ResponseCode;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;
import cl.thinka.clientmicroservice.v1.jpa.entity.Task;
import cl.thinka.clientmicroservice.v1.jpa.repository.TaskRepository;
import cl.thinka.clientmicroservice.v1.mapper.TaskMapper;
import cl.thinka.clientmicroservice.v1.service.ITaskService;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;

    @Override
    public ThinkaResponse listTasks() throws ThinkaException {
        try {
            List<TaskDTO> tasksDTO = new ArrayList<>();
            List<Task> tasks = taskRepository.getAll();
            for (Task task : tasks) {
                TaskDTO taskDTO = new TaskDTO();
                taskDTO = TaskMapper.INSTANCE.toDto(task);
                tasksDTO.add(taskDTO);
            }
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), tasksDTO);
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse getTaskById(Long id) throws ThinkaException {
        TaskDTO response;
        try {
            Task task = taskRepository.getById(id);
            if(task == null){
                log.error("Error on get task. Task not found ");
                return new ThinkaResponse(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), "Task not found.");
            }
            response = TaskMapper.INSTANCE.toDto(task);
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), response);
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    @Transactional
    public ThinkaResponse deleteTaskById(Long id) throws ThinkaException {
        try {
            Task task = taskRepository.getById(id);
            if(task == null){
                log.error("Error on delete task. Task not found ");
                return new ThinkaResponse(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), "Task not found.");
            }
            this.taskRepository.deleteById(id, 1L);
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), "Deleted successfully.");
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse editTask(EditTaskRequestDTO task) throws ThinkaException {
        try {
            log.info(TaskServiceImpl.class + " SUCCESS ");
            Task existingTask = this.taskRepository.getById(task.getId());

            if(existingTask == null){
                log.error("Error on edit task. Task not found ");
                return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), "Error on edit task. Task not found ");
            }
            Task newTask = TaskMapper.INSTANCE.toEntity(task);
            newTask.setLastModifiedBy(1L);

            if (task.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
                newTask.setStartDate(LocalDateTime.parse(task.getStartDate()));
            } else {
                throw new ThinkaException("Invalid Start Date format. Please use YYYY-MM-DD'T'HH:mm:ss format.");
            }

            if (task.getEndDate().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
                newTask.setStartDate(LocalDateTime.parse(task.getEndDate()));
            } else {
                throw new ThinkaException("Invalid End Date format. Please use YYYY-MM-DD'T'HH:mm:ss format.");
            }


            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), this.taskRepository.save(newTask));
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse saveTask(AddTaskRequestDTO task) throws ThinkaException {
        try {
            Task newTask = TaskMapper.INSTANCE.toEntity(task);
            newTask.setStatus(1);
            newTask.setCreatedBy(1L);

            if (task.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newTask.setStartDate(LocalDateTime.parse(task.getStartDate()+" 00:00:00", formatter));
            } else {
                throw new ThinkaException("Invalid Start Date format. Please use YYYY-MM-DD format.");
            }

            if (task.getEndDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newTask.setEndDate(LocalDateTime.parse(task.getEndDate() + " 23:59:59", formatter));
            } else {
                throw new ThinkaException("Invalid End Date format. Please use YYYY-MM-DD format.");
            }

            log.info(TaskServiceImpl.class + " SUCCESS ");
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), this.taskRepository.save(newTask));
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }
}
