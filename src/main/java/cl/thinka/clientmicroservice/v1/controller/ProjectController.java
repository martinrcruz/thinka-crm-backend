package cl.thinka.clientmicroservice.v1.controller;


import cl.thinka.clientmicroservice.v1.dto.ProjectDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddProjectRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditProjectRequestDTO;
import cl.thinka.clientmicroservice.v1.enums.ResponseCode;
import cl.thinka.clientmicroservice.v1.service.IProjectService;
import cl.thinka.clientmicroservice.v1.service.impl.ProjectServiceImpl;
import cl.thinka.clientmicroservice.v1.util.ThinkaHttpBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
@Log4j2
public class ProjectController {

    private final IProjectService projectService;

    @Operation(summary = "List all active projects")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/list")
    public ResponseEntity<ThinkaResponse> listProjects() {
        return ThinkaHttpBuilder.buildHttpResponse(this.projectService.listProjects());
    }

    @Operation(summary = "Get a project by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ThinkaResponse> getProjectById(@PathVariable Long id) {
        return ThinkaHttpBuilder.buildHttpResponse(this.projectService.getProjectById(id));
    }

    @Operation(summary = "Create a new Project")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PostMapping(value = "/add")
    public ResponseEntity<ThinkaResponse> addProject(@RequestBody AddProjectRequestDTO project) {
        return ThinkaHttpBuilder.buildHttpResponse(this.projectService.saveProject(project));
    }

    @Operation(summary = "Edit a Project")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PutMapping(value = "/edit")
    public ResponseEntity<ThinkaResponse> editProject(@RequestBody EditProjectRequestDTO project) {
        return ThinkaHttpBuilder.buildHttpResponse(this.projectService.editProject(project));
    }

    @Operation(summary = "Delete a Project")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ThinkaResponse> deleteProject(@PathVariable Long id) {
        return ThinkaHttpBuilder.buildHttpResponse(this.projectService.deleteProjectById(id));
    }


}
