package api.invoice.reports.service;

import api.invoice.reports.request.UploadFile;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UploadFileService {

    public String uploadFileAzure(UploadFile uploadFile) {
        String storageConnectionAzure = "DefaultEndpointsProtocol=https;AccountName=sto1repo;AccountKey=G5lkZkSDvrTLbnr5BIw3KJrHDvwvMh0lt7pf6WcQlPYulpOiv8xvO2GogrTDMLEGos4cqFbiQj+T+AStfKLEDQ==;EndpointSuffix=core.windows.net";
        String resultService = "";
        String containerName = "reports";
        try{
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionAzure);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference(containerName);
            var blob = container.getBlockBlobReference(uploadFile.getName());
            byte[] decodedBytes = Base64.getDecoder().decode(uploadFile.getFileBase64());
            blob.uploadFromByteArray(decodedBytes, 0,decodedBytes.length);

        }catch (Exception e){
            resultService = e.getMessage();
        }

        return resultService;
    }

}
