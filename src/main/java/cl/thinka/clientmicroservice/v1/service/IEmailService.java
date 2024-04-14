package cl.thinka.clientmicroservice.v1.service;

import cl.thinka.clientmicroservice.v1.dto.EmailDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddEmailRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.SendEmailRequestDTO;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;

import java.util.List;

public interface IEmailService {
    public List<EmailDTO> listEmails() throws ThinkaException;
    public EmailDTO getEmailById(Long id) throws ThinkaException;
    public Boolean deleteEmailById(Long id) throws ThinkaException;
    public EmailDTO saveEmail(AddEmailRequestDTO email) throws ThinkaException;
    public boolean sendEmail(SendEmailRequestDTO email) throws ThinkaException;
}
