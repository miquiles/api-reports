package api.invoice.reports.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class FileToBase64Converter {
    public static String convertFileToBase64(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("O arquivo não existe.");
        }

        byte[] fileBytes = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(fileBytes);
        } catch (IOException e) {
            throw new IOException("Erro ao ler o arquivo.", e);
        }

        byte[] base64Bytes = Base64.getEncoder().encode(fileBytes);

        String base64String = new String(base64Bytes);

        return base64String;
    }

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Pedro Henrique\\reports\\boleto.pdf"); // Substitua pelo caminho do seu arquivo
            String base64String = convertFileToBase64(file);
            System.out.println("Arquivo em Base64:\n" + base64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

