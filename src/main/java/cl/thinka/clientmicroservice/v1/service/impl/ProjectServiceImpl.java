package cl.thinka.clientmicroservice.v1.service.impl;

import cl.thinka.clientmicroservice.v1.dto.ProjectDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddProjectRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditProjectRequestDTO;
import cl.thinka.clientmicroservice.v1.enums.ResponseCode;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;
import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import cl.thinka.clientmicroservice.v1.jpa.repository.ProjectRepository;
import cl.thinka.clientmicroservice.v1.mapper.ProjectMapper;
import cl.thinka.clientmicroservice.v1.mapper.ProjectMapper;
import cl.thinka.clientmicroservice.v1.service.IProjectService;
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
public class ProjectServiceImpl implements IProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ThinkaResponse listProjects() throws ThinkaException {
        try {
            List<ProjectDTO> projectsDTO = new ArrayList<>();
            List<Project> projects = projectRepository.getAll();
            for (Project project : projects) {
                ProjectDTO projectDTO = new ProjectDTO();
                projectDTO = ProjectMapper.INSTANCE.toDto(project);
                projectsDTO.add(projectDTO);
            }
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), projectsDTO);
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse getProjectById(Long id) throws ThinkaException {
        ProjectDTO response;
        try {
            Project project = projectRepository.getById(id);
            if(project == null){
                log.error("Error on get project. Project not found ");
                return new ThinkaResponse(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), "Project not found.");
            }
            response = ProjectMapper.INSTANCE.toDto(project);
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), response);
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    @Transactional
    public ThinkaResponse deleteProjectById(Long id) throws ThinkaException {
        try {
            Project project = projectRepository.getById(id);
            if(project == null){
                log.error("Error on delete project. Project not found ");
                return new ThinkaResponse(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), "Project not found.");
            }
            this.projectRepository.deleteById(id, 1L);
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), "Deleted successfully.");
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse editProject(EditProjectRequestDTO project) throws ThinkaException {
        try {
            Project existingProject = this.projectRepository.getById(project.getId());
            if(existingProject == null){
                log.error("Error creating project. Already exists.");
                return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), "Error creating project. Already exists.");
            }
            Project newProject = ProjectMapper.INSTANCE.toEntity(project);
            newProject.setLastModifiedBy(1L);

            if (project.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newProject.setStartDate(LocalDateTime.parse(project.getStartDate()+" 00:00:00", formatter));
            } else {
                throw new ThinkaException("Invalid Start Date format. Please use YYYY-MM-DD format.");
            }

            if (project.getEndDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newProject.setEndDate(LocalDateTime.parse(project.getEndDate() + " 23:59:59", formatter));
            } else {
                throw new ThinkaException("Invalid End Date format. Please use YYYY-MM-DD format.");
            }

            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),this.projectRepository.save(newProject));
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse saveProject(AddProjectRequestDTO project) throws ThinkaException {
        try {
            Project newProject = ProjectMapper.INSTANCE.toEntity(project);
            newProject.setStatus(1);
            newProject.setCreatedBy(1L);

            if (project.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newProject.setStartDate(LocalDateTime.parse(project.getStartDate()+" 00:00:00", formatter));
            } else {
                throw new ThinkaException("Invalid Start Date format. Please use YYYY-MM-DD format.");
            }

            if (project.getEndDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newProject.setEndDate(LocalDateTime.parse(project.getEndDate() + " 23:59:59", formatter));
            } else {
                throw new ThinkaException("Invalid End Date format. Please use YYYY-MM-DD format.");
            }

            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),this.projectRepository.save(newProject));
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }
}
