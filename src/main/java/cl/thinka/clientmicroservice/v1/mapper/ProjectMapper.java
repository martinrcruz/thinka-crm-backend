package cl.thinka.clientmicroservice.v1.mapper;

import cl.thinka.clientmicroservice.v1.dto.ProjectDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddProjectRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditProjectRequestDTO;
import cl.thinka.clientmicroservice.v1.jpa.entity.File;
import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
    Project toEntity(ProjectDTO project);

    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    Project toEntity(EditProjectRequestDTO project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    Project toEntity(AddProjectRequestDTO project);

    ProjectDTO toDto(Project project);
    EditProjectRequestDTO toEditRequest(Project project);
    AddProjectRequestDTO toAddRequest(Project project);

}
