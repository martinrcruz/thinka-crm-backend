package cl.thinka.clientmicroservice.v1.mapper;

import cl.thinka.clientmicroservice.v1.dto.SaleDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddSaleRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditSaleRequestDTO;
import cl.thinka.clientmicroservice.v1.jpa.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleMapper {
    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);
    Sale toEntity(SaleDTO sale);

    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    Sale toEntity(EditSaleRequestDTO sale);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    Sale toEntity(AddSaleRequestDTO sale);
    SaleDTO toDto(Sale sale);
    EditSaleRequestDTO toEditRequest(Sale sale);
    AddSaleRequestDTO toAddRequest(Sale sale);

}
