package cl.thinka.clientmicroservice.v1.jpa.repository.impl;

import cl.thinka.clientmicroservice.v1.jpa.entity.Email;
import cl.thinka.clientmicroservice.v1.jpa.repository.EmailRepository;
import cl.thinka.clientmicroservice.v1.jpa.repository.read.EmailRepositoryRead;
import cl.thinka.clientmicroservice.v1.jpa.repository.write.EmailRepositoryWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailRepositoryImpl implements EmailRepository {
    @Autowired
    EmailRepositoryRead emailRepositoryRead;
    @Autowired
    EmailRepositoryWrite emailRepositoryWrite;

    @Override
    public Email save(Email email) {
        return emailRepositoryWrite.save(email);
    }

    @Override
    public boolean deleteById(Long id, Long userId) {
        emailRepositoryWrite.deleteById(id, userId);
        return true;
    }

    @Override
    public Email getById(Long id) {
        return emailRepositoryRead.getEmailById(id);
    }

    @Override
    public List<Email> getAll() {
        return emailRepositoryRead.findAll();
    }
}
