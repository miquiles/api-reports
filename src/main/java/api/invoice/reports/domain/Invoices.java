package api.invoice.reports.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long register;
    private String barcode;
    private LocalDate dueDate;
    private LocalDateTime registerDate;
    private String receiverDocument;
    private String receiverName;
    private String bankName;
    private String agency;
    private String number;
    private BigDecimal amount;

}
