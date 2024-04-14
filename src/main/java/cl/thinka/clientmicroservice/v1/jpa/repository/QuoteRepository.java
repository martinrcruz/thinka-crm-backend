package cl.thinka.clientmicroservice.v1.jpa.repository;

import cl.thinka.clientmicroservice.v1.jpa.entity.Quote;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository {

    Quote save(Quote quote);

    boolean deleteById(Long id, Long userId);

    Quote getById(Long id);

    List<Quote> getAll();

}
