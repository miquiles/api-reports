package api.invoice.reports.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


@Data
@AllArgsConstructor
public class AzureConfig {

    @Value("${azure.storage.connection-string}")
    private String storageConnectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

}
