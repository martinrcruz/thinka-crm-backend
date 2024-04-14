package cl.thinka.clientmicroservice.v1.jpa.repository.read;

import cl.thinka.clientmicroservice.v1.jpa.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepositoryRead extends JpaRepository<Quote, Long> {
    @Query("select q from Quote q where q.id = ?1 and q.status = 1")
    Quote getQuoteById(Long id);

    @Query("select q from Quote q where q.status = 1")
    List<Quote> findAll();
}
