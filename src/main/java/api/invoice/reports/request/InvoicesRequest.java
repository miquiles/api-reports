package api.invoice.reports.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class InvoicesRequest {
    private String barcode;
    private LocalDate dueDate;
    private String receiverDocument;
    private String receiverName;
    private String bankName;
    private String agency;
    private String number;
    private BigDecimal amount;
    private Long register;
}
