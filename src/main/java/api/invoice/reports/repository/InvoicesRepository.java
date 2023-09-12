package api.invoice.reports.repository;

import api.invoice.reports.domain.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long> {

    Optional<Invoices> findByRegister(Long register);
}
