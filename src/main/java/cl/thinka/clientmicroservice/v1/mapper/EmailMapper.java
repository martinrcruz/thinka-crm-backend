package cl.thinka.clientmicroservice.v1.mapper;

import cl.thinka.clientmicroservice.v1.dto.EmailDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddEmailRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.SendEmailRequestDTO;
import cl.thinka.clientmicroservice.v1.jpa.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmailMapper {
    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);
    Email toEntity(EmailDTO client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    Email toEntity(AddEmailRequestDTO client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    Email toEntity(SendEmailRequestDTO client);
    EmailDTO toDto(Email email);
    AddEmailRequestDTO toAddRequest(Email email);

}
