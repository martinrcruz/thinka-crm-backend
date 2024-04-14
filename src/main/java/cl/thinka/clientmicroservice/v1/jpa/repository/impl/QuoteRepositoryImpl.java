package cl.thinka.clientmicroservice.v1.jpa.repository.impl;

import cl.thinka.clientmicroservice.v1.jpa.entity.Quote;
import cl.thinka.clientmicroservice.v1.jpa.repository.QuoteRepository;
import cl.thinka.clientmicroservice.v1.jpa.repository.read.QuoteRepositoryRead;
import cl.thinka.clientmicroservice.v1.jpa.repository.write.QuoteRepositoryWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuoteRepositoryImpl implements QuoteRepository {
    @Autowired
    QuoteRepositoryRead quoteRepositoryRead;
    @Autowired
    QuoteRepositoryWrite quoteRepositoryWrite;

    @Override
    public Quote save(Quote quote) {
        return quoteRepositoryWrite.save(quote);
    }

    @Override
    public boolean deleteById(Long id, Long userId) {
        quoteRepositoryWrite.deleteById(id, userId);
        return true;
    }

    @Override
    public Quote getById(Long id) {
        return quoteRepositoryRead.getQuoteById(id);
    }

    @Override
    public List<Quote> getAll() {
        return quoteRepositoryRead.findAll();
    }
}
