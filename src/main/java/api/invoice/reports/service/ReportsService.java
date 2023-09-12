package api.invoice.reports.service;

import api.invoice.reports.domain.Invoices;
import api.invoice.reports.repository.InvoicesRepository;
import api.invoice.reports.request.UploadFile;
import api.invoice.reports.utils.FileToBase64Converter;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReportsService {
    private final InvoicesRepository invoicesRepository;
    private UploadFileService uploadFileAzure;
    private final String PATH = "C:\\Users\\Pedro Henrique\\reports";

    public String export(String reportFormat) throws FileNotFoundException, JRException {
        var listRepors = invoicesRepository.findAll();
        var path = ResourceUtils.getFile("classpath:invoice.jrxml");
        var jasperReport = JasperCompileManager.compileReport(path.getAbsolutePath());
        var dataSource = new JRBeanCollectionDataSource(listRepors);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy" , "Java Techie");
        var jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, PATH +"\\invoice.html");
        }if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, PATH +"\\invoice.pdf");
        }

        return "export done";
    }

    public void generateReport(Invoices invoices, String reportFormat) throws JRException, IOException {
        var data = Collections.singletonList(invoices);
        var path = ResourceUtils.getFile("classpath:boleto.jrxml");
        var jasperReport = JasperCompileManager.compileReport(path.getAbsolutePath());
        var dataSource = new JRBeanCollectionDataSource(data);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy", "Java Techie");

        var jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        var uploadFile = new UploadFile();

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, PATH + "\\boleto.html");
            uploadFile.setName("boleto.html");
        } else if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, PATH + "\\boleto.pdf");
            uploadFile.setName("boleto.pdf");
        }

        uploadFile.setFileBase64(FileToBase64Converter.convertFileToBase64(path));
        uploadFileAzure.uploadFileAzure(uploadFile);

    }




}
