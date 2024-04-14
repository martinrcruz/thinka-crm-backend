package cl.thinka.clientmicroservice.v1.service;

import cl.thinka.clientmicroservice.v1.dto.request.AddClientRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditClientRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.WebFormQuoteRequestDTO;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;

import java.util.List;

public interface IClientService {

    public ThinkaResponse listClients() throws ThinkaException;
    public ThinkaResponse getClientById(Long id) throws ThinkaException;
    public ThinkaResponse deleteClientById(Long id) throws ThinkaException;
    public ThinkaResponse editClient(EditClientRequestDTO client) throws ThinkaException;
    public ThinkaResponse saveClient(AddClientRequestDTO client) throws ThinkaException;
    public ThinkaResponse saveWebClient(WebFormQuoteRequestDTO client) throws ThinkaException;
    }
