package api.invoice.reports.controller;

import api.invoice.reports.domain.Invoices;
import api.invoice.reports.repository.InvoicesRepository;
import api.invoice.reports.request.InvoicesRequest;
import api.invoice.reports.service.InvoiceService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.jfree.util.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/invoices")
@AllArgsConstructor
@RestController
@Slf4j
public class InvoicesController {

    private final InvoiceService invoiceService;
    private final InvoicesRepository invoicesRepository;

    @PostMapping
    public ResponseEntity<Invoices> create(@RequestBody InvoicesRequest req, @RequestParam String format){
        try {
            log.error("GERANDO BOLETO - REGISTRO :" + req.getRegister());
            return new ResponseEntity<>(invoiceService.create(req, format), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Invoices> getAll(){
        return invoicesRepository.findAll();
    }
}
