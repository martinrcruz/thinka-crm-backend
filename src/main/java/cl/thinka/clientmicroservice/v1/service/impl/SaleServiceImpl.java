package cl.thinka.clientmicroservice.v1.service.impl;

import cl.thinka.clientmicroservice.v1.dto.SaleDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddSaleRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditSaleRequestDTO;
import cl.thinka.clientmicroservice.v1.enums.ResponseCode;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;
import cl.thinka.clientmicroservice.v1.jpa.entity.Quote;
import cl.thinka.clientmicroservice.v1.jpa.entity.Sale;
import cl.thinka.clientmicroservice.v1.jpa.repository.SaleRepository;
import cl.thinka.clientmicroservice.v1.mapper.SaleMapper;
import cl.thinka.clientmicroservice.v1.service.ISaleService;
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
public class SaleServiceImpl implements ISaleService {

    private final SaleRepository saleRepository;

    @Override
    public ThinkaResponse listSales() throws ThinkaException {
        try {
            List<SaleDTO> salesDTO = new ArrayList<>();
            List<Sale> sales = saleRepository.getAll();
            for (Sale sale : sales) {
                SaleDTO saleDTO;
                saleDTO = SaleMapper.INSTANCE.toDto(sale);
                salesDTO.add(saleDTO);
            }
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), salesDTO);
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse getSaleById(Long id) throws ThinkaException {
        SaleDTO response;
        try {
            Sale sale = saleRepository.getById(id);
            if(sale == null){
                log.error("Error on get sale. Sale not found ");
                return new ThinkaResponse(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), "Sale not found.");
            }
            response = SaleMapper.INSTANCE.toDto(sale);
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), response);
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    @Transactional
    public ThinkaResponse deleteSaleById(Long id) throws ThinkaException {
        try {
            Sale sale = saleRepository.getById(id);
            if(sale == null){
                log.error("Error on delete sale. Sale not found ");
                return new ThinkaResponse(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), "Sale not found.");
            }
            this.saleRepository.deleteById(id, 1L);
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), "Deleted successfully.");
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse editSale(EditSaleRequestDTO sale) throws ThinkaException {
        try {
            log.info(SaleServiceImpl.class + " SUCCESS ");
            Sale existingSale = this.saleRepository.getById(sale.getId());
            if (existingSale == null) {
                log.error("Error on edit sale. Sale not found ");
                return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), "Error on edit sale. Sale not found ");
            }
            Sale newSale = SaleMapper.INSTANCE.toEntity(sale);
            newSale.setLastModifiedBy(1L);

            if (sale.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newSale.setStartDate(LocalDateTime.parse(sale.getStartDate()+" 00:00:00", formatter));
            } else {
                throw new ThinkaException("Invalid Start Date format. Please use YYYY-MM-DD format.");
            }

            if (sale.getEndDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newSale.setEndDate(LocalDateTime.parse(sale.getEndDate() + " 23:59:59", formatter));
            } else {
                throw new ThinkaException("Invalid End Date format. Please use YYYY-MM-DD format.");
            }

            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), this.saleRepository.save(newSale));
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse saveSale(AddSaleRequestDTO sale) throws ThinkaException {
        try {
            Sale newSale = SaleMapper.INSTANCE.toEntity(sale);
            newSale.setStatus(1);
            newSale.setCreatedBy(1L);

            if (sale.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newSale.setStartDate(LocalDateTime.parse(sale.getStartDate()+" 00:00:00", formatter));
            } else {
                throw new ThinkaException("Invalid Start Date format. Please use YYYY-MM-DD format.");
            }

            if (sale.getEndDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newSale.setEndDate(LocalDateTime.parse(sale.getEndDate() + " 23:59:59", formatter));
            } else {
                throw new ThinkaException("Invalid End Date format. Please use YYYY-MM-DD format.");
            }

            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), this.saleRepository.save(newSale));
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }
}
