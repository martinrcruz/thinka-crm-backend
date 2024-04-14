package cl.thinka.clientmicroservice.v1.service.impl;

import cl.thinka.clientmicroservice.v1.dto.ClientDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddClientRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditClientRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.SendEmailDTO;
import cl.thinka.clientmicroservice.v1.dto.request.WebFormQuoteRequestDTO;
import cl.thinka.clientmicroservice.v1.enums.ResponseCode;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;
import cl.thinka.clientmicroservice.v1.jpa.entity.Client;
import cl.thinka.clientmicroservice.v1.jpa.repository.ClientRepository;
import cl.thinka.clientmicroservice.v1.jpa.repository.ProjectRepository;
import cl.thinka.clientmicroservice.v1.mapper.ClientMapper;
import cl.thinka.clientmicroservice.v1.service.IClientService;
import cl.thinka.clientmicroservice.v1.service.IEmailService;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    private final ProjectRepository projectRepository;

    private IEmailService mailService;


    @Override
    public ThinkaResponse listClients() throws ThinkaException {
        try {
            List<ClientDTO> clientsDTO = new ArrayList<>();
            List<Client> clients = clientRepository.getAll();
            for (Client client : clients) {
                ClientDTO clientDTO = new ClientDTO();
                clientDTO = ClientMapper.INSTANCE.toDto(client);
                clientsDTO.add(clientDTO);
            }
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), clientsDTO);

        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse getClientById(Long id) throws ThinkaException {
        ClientDTO response;
        try {
            Client client = clientRepository.getById(id);
            if(client == null){
                log.error("Error on get client. Client not found ");
                return new ThinkaResponse(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), "Client not found.");
            }
            response = ClientMapper.INSTANCE.toDto(client);
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), response);
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    @Transactional
    public ThinkaResponse deleteClientById(Long id) throws ThinkaException {
        try {
            Client client = clientRepository.getById(id);
            if (client == null) {
                log.error("Error on get client. Client not found ");
                return new ThinkaResponse(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), "Client not found.");
            }
            this.clientRepository.deleteById(id, 1L);
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), "Deleted successfully.");
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    @Transactional
    public ThinkaResponse editClient(EditClientRequestDTO client) throws ThinkaException {
        try {
            log.info(ClientServiceImpl.class + " SUCCESS ");
            Client existingClient = this.clientRepository.getById(client.getId());
            if(existingClient == null){
                log.error(ClientServiceImpl.class + "Error: Cannot create client. Already exists.");
                return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), "Cannot create client. Already exists.");
            }
            Client newClient = ClientMapper.INSTANCE.toEntity(client);
            newClient.setLastModifiedBy(1L);
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),this.clientRepository.save(newClient));
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse saveClient(AddClientRequestDTO client) throws ThinkaException {
        try {
            Client newClient = ClientMapper.INSTANCE.toEntity(client);
            newClient.setCreatedBy(1L);
            log.info(ClientServiceImpl.class + " SUCCESS ");
            return new ThinkaResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),this.clientRepository.save(newClient));
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }

    @Override
    public ThinkaResponse saveWebClient(WebFormQuoteRequestDTO client) throws ThinkaException {
        try {
            Client newClient = ClientMapper.INSTANCE.toEntity(client);
            log.info(ClientServiceImpl.class + " SUCCESS ");
            if (this.clientRepository.saveFromWeb(newClient)) {
                SendEmailDTO email = new SendEmailDTO();

                email.setTitle("Un nuevo usuario ha llenado el formulario web.");
                email.setSubject("Martin, un nuevo usuario ha visitado la pagina");
                email.setFromEmail("equipo@srweb.cl");
                email.setToEmail("martin@srweb.cl");
                email.setMessage("Martin, un nuevo visitante ha llenado el formulario de la pagina web. Wen abogao!");

//                this.mailService.sendMail(email);
//                return ClientMapper.INSTANCE.toDto(newClient);
            }
            return null;

        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return new ThinkaResponse(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), e.getMessage());
        }
    }
}
