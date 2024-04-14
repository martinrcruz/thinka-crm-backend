package cl.thinka.clientmicroservice.v1.jpa.repository.read;

import cl.thinka.clientmicroservice.v1.jpa.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaleRepositoryRead extends JpaRepository<Sale, Long> {

    @Query("select s from Sale s where s.id = ?1 and s.status = 1")
    Sale getSaleById(Long id);

    @Query("select s from Sale s where s.status = 1")
    List<Sale> findAll();
}
