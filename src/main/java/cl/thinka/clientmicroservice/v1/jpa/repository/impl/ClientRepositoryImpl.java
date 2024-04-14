package cl.thinka.clientmicroservice.v1.jpa.repository.impl;

import cl.thinka.clientmicroservice.v1.jpa.entity.Client;
import cl.thinka.clientmicroservice.v1.jpa.repository.ClientRepository;
import cl.thinka.clientmicroservice.v1.jpa.repository.read.ClientRepositoryRead;
import cl.thinka.clientmicroservice.v1.jpa.repository.write.ClientRepositoryWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    @Autowired
    ClientRepositoryRead clientRepositoryRead;
    @Autowired
    ClientRepositoryWrite clientRepositoryWrite;

    @Override
    public Client save(Client client) {
        return clientRepositoryWrite.save(client);
    }

    @Override
    public boolean deleteById(Long id, Long userId) {
        clientRepositoryWrite.deleteById(id, userId);
        return true;
    }

    @Override
    public Client getById(Long id) {
        return clientRepositoryRead.getClientById(id);
    }

    @Override
    public List<Client> getAll() {
        return clientRepositoryRead.findAll();
    }

    @Override
    public boolean saveFromWeb(Client client) {
        clientRepositoryWrite.save(client);
        return true;
    }
}
