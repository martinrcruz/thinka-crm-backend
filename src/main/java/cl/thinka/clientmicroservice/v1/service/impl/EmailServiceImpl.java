package cl.thinka.clientmicroservice.v1.service.impl;

import cl.thinka.clientmicroservice.v1.dto.EmailDTO;
import cl.thinka.clientmicroservice.v1.dto.request.AddEmailRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.SendEmailRequestDTO;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;
import cl.thinka.clientmicroservice.v1.jpa.entity.Email;
import cl.thinka.clientmicroservice.v1.jpa.repository.EmailRepository;
import cl.thinka.clientmicroservice.v1.mapper.EmailMapper;
import cl.thinka.clientmicroservice.v1.service.IEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailServiceImpl implements IEmailService {

    private final EmailRepository emailRepository;

    private JavaMailSender mailSender;

    @Override
    public List<EmailDTO> listEmails() throws ThinkaException {
        try {
            List<EmailDTO> salesDTO = new ArrayList<>();
            List<Email> emails = emailRepository.getAll();
            for (Email email : emails) {
                EmailDTO saleDTO = new EmailDTO();
                saleDTO = EmailMapper.INSTANCE.toDto(email);
                salesDTO.add(saleDTO);
            }
            return salesDTO;
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public EmailDTO getEmailById(Long id) throws ThinkaException {
        EmailDTO response = new EmailDTO();
        try {
            Email email = emailRepository.getById(id);
            response = EmailMapper.INSTANCE.toDto(email);
            return response;
        } catch (ThinkaException e) {
            log.error("Error: " + e.getMessage());
            return response;
        }
    }

    @Override
    @Transactional
    public Boolean deleteEmailById(Long id) throws ThinkaException {
        try {
            return this.emailRepository.deleteById(id, 1L);
        } catch (ThinkaException e) {
            log.error(EmailServiceImpl.class + "Error" + e.getMessage());
            return null;
        }
    }

    @Override
    public EmailDTO saveEmail(AddEmailRequestDTO sale) throws ThinkaException {
        try {
            Email newEmail = EmailMapper.INSTANCE.toEntity(sale);
            log.info(EmailServiceImpl.class + " SUCCESS ");
            return EmailMapper.INSTANCE.toDto(this.emailRepository.save(newEmail));
        } catch (ThinkaException e) {
            log.error(EmailServiceImpl.class + "Error" + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean sendEmail(SendEmailRequestDTO email) throws ThinkaException {
        try {
            Email newEmail = EmailMapper.INSTANCE.toEntity(email);
            log.info(EmailServiceImpl.class + " [EXECUTING PROCESS] -> Requesting sendEmail service function. ");
            log.info(EmailServiceImpl.class + " [REQUEST DATA] Title: " + email.getTitle());
            log.info(EmailServiceImpl.class + " [REQUEST DATA] Subject: " + email.getSubject());
            log.info(EmailServiceImpl.class + " [REQUEST DATA] From: " + email.getFromEmail());
            log.info(EmailServiceImpl.class + " [REQUEST DATA] To: " + email.getToEmail());
            log.info(EmailServiceImpl.class + " [REQUEST DATA] Message: " + email.getMessage());

            log.info(EmailServiceImpl.class + " [WAITING PROCESS] -> Sending email. ");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getToEmail());
            message.setFrom(email.getFromEmail());
            message.setSubject(email.getSubject());
            message.setText(email.getMessage());
            mailSender.send(message);

            log.info(EmailServiceImpl.class + " [SUCCESS] EMAIL SENT!");
            this.emailRepository.save(newEmail);

            return true;
        } catch (ThinkaException e) {

            log.error(EmailServiceImpl.class + "[ERROR] -> " + e.getMessage());
            return false;
        }
    }

}
