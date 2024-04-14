package cl.thinka.clientmicroservice.v1.jpa.repository.write;

import cl.thinka.clientmicroservice.v1.jpa.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepositoryWrite extends JpaRepository<Quote, Long> {

    @Modifying
    @Query("update Quote q set q.status = 0, q.deletedAt = now(), q.deletedBy = ?2 where q.id = ?1")
    void deleteById(Long id, Long userId);

}
