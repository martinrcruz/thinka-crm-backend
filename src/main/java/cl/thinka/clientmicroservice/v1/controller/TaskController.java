package cl.thinka.clientmicroservice.v1.controller;

import cl.thinka.clientmicroservice.v1.dto.TaskDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddTaskRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditTaskRequestDTO;
import cl.thinka.clientmicroservice.v1.enums.ResponseCode;
import cl.thinka.clientmicroservice.v1.service.ITaskService;
import cl.thinka.clientmicroservice.v1.service.impl.TaskServiceImpl;
import cl.thinka.clientmicroservice.v1.util.ThinkaHttpBuilder;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
@Log4j2
public class TaskController {

    private final ITaskService taskService;

    @Operation(summary = "List all active tasks")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/list")
    public ResponseEntity<ThinkaResponse> listTasks() {
        return ThinkaHttpBuilder.buildHttpResponse(this.taskService.listTasks());
    }

    @Operation(summary = "Get a task by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ThinkaResponse> getTaskById(@PathVariable Long id) {
        return ThinkaHttpBuilder.buildHttpResponse(this.taskService.getTaskById(id));
    }

    @Operation(summary = "Create a new Task")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PostMapping(value = "/add")
    public ResponseEntity<ThinkaResponse> addTask(@RequestBody AddTaskRequestDTO task) {
        return ThinkaHttpBuilder.buildHttpResponse(this.taskService.saveTask(task));
    }

    @Operation(summary = "Edit a Task")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PutMapping(value = "/edit")
    public ResponseEntity<ThinkaResponse> editTask(@RequestBody EditTaskRequestDTO task) {
        return ThinkaHttpBuilder.buildHttpResponse(this.taskService.editTask(task));

    }

    @Operation(summary = "Delete a Task")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ThinkaResponse> deleteTask(@PathVariable Long id) {
        return ThinkaHttpBuilder.buildHttpResponse(this.taskService.deleteTaskById(id));

    }
}
