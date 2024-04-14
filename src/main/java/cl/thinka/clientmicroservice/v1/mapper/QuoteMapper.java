package cl.thinka.clientmicroservice.v1.mapper;

import cl.thinka.clientmicroservice.v1.dto.QuoteDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddQuoteRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditQuoteRequestDTO;
import cl.thinka.clientmicroservice.v1.jpa.entity.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuoteMapper {
    QuoteMapper INSTANCE = Mappers.getMapper(QuoteMapper.class);
    Quote toEntity(QuoteDTO quote);

    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    Quote toEntity(EditQuoteRequestDTO quote);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    Quote toEntity(AddQuoteRequestDTO quote);
    QuoteDTO toDto(Quote sale);
    EditQuoteRequestDTO toEditRequest(Quote sale);
    AddQuoteRequestDTO toAddRequest(Quote sale);

}
