package kz.noise.converter.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DocxConverterImpl implements DocxConverter {

    @Override
    public byte[] convertDocxToPdf(MultipartFile file) {
        Logger logger = Logger.getGlobal();
        String serverUrl = "http://localhost:3000/convert/office";
        try {
            Long start = System.currentTimeMillis();
            HttpPost post = new HttpPost(serverUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addBinaryBody("file", file.getBytes(), ContentType.APPLICATION_OCTET_STREAM, file.getOriginalFilename());
            HttpEntity entity = builder.build();
            post.setEntity(entity);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(post);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            Long end = System.currentTimeMillis();
            logger.info("converted docx to pdf for " + (end - start));
            return out.toByteArray();
        } catch (IOException ex) {
            logger.log(Level.WARNING, "Cant convert docx to pdf");
            return null;
        }
    }
}
