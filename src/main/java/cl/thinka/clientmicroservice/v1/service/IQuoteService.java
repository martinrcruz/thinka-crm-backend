package cl.thinka.clientmicroservice.v1.service;

import cl.thinka.clientmicroservice.v1.dto.request.AddQuoteRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditQuoteRequestDTO;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;

import java.util.List;

public interface IQuoteService {

    public ThinkaResponse listQuotes() throws ThinkaException;
    public ThinkaResponse getQuoteById(Long id) throws ThinkaException;
    public ThinkaResponse deleteQuoteById(Long id) throws ThinkaException;
    public ThinkaResponse editQuote(EditQuoteRequestDTO quote) throws ThinkaException;
    public ThinkaResponse saveQuote(AddQuoteRequestDTO quote) throws ThinkaException;
}
