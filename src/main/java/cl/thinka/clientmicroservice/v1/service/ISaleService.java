package cl.thinka.clientmicroservice.v1.service;

import cl.thinka.clientmicroservice.v1.dto.SaleDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddSaleRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditSaleRequestDTO;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;

import java.util.List;

public interface ISaleService {

    public ThinkaResponse listSales() throws ThinkaException;
    public ThinkaResponse getSaleById(Long id) throws ThinkaException;
    public ThinkaResponse deleteSaleById(Long id) throws ThinkaException;
    public ThinkaResponse editSale(EditSaleRequestDTO client) throws ThinkaException;
    public ThinkaResponse saveSale(AddSaleRequestDTO client) throws ThinkaException;
}
