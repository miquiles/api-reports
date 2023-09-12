package api.invoice.reports.controller;

import api.invoice.reports.service.ReportsService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RequestMapping("/reports")
@AllArgsConstructor
@RestController
public class ReportsController {

    private final ReportsService reportsService;

    @GetMapping("/{format}")
    public String generate(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportsService.export(format);
    }
}
