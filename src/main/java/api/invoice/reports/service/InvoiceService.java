package api.invoice.reports.service;

import api.invoice.reports.domain.Invoices;
import api.invoice.reports.repository.InvoicesRepository;
import api.invoice.reports.request.InvoicesRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class InvoiceService {

    private final InvoicesRepository invoicesRepository;
    private final ReportsService reportsService;

    public Invoices create(InvoicesRequest req, String format) throws Exception {
        var invoice = toInvoice(req);
        var existsInvoice = getByRegister(invoice.getRegister());
        if(existsInvoice.isEmpty()){
            reportsService.generateReport(invoice, format);
            log.error("BOLETO SALVO NO CONTAINER REPORTS");
            return invoicesRepository.save(invoice);
        }else{
            throw new Exception("Já existe uma invoice com esse registro");
        }
    }

    public Optional<Invoices> getByRegister(Long register) {
        return invoicesRepository.findByRegister(register);
    }

    public Invoices toInvoice(InvoicesRequest req) {
        return Invoices.builder()
                .register(req.getRegister())
                .registerDate(LocalDateTime.now())
                .dueDate(req.getDueDate())
                .barcode(req.getBarcode())
                .amount(req.getAmount())
                .bankName(req.getBankName())
                .receiverDocument(req.getReceiverDocument())
                .receiverName(req.getReceiverName())
                .number(req.getNumber())
                .agency(req.getAgency())
                .build();
    }

}
