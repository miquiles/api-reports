package api.invoice.reports.request;

import lombok.Data;

@Data
public class UploadFile {
    private String name;
    private String fileBase64;
}

