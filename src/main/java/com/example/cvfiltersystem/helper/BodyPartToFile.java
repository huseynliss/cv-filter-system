package com.example.cvfiltersystem.helper;

import org.springframework.web.multipart.MultipartFile;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import java.io.*;

public class BodyPartToFile implements MultipartFile {

    private final BodyPart bodyPart;
    private final String name;
    private final String originalFilename;
    private final String contentType;

    public BodyPartToFile(BodyPart bodyPart, String name, String originalFilename, String contentType) {
        this.bodyPart = bodyPart;
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getOriginalFilename() {
        return this.originalFilename;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public boolean isEmpty() {
        return false; // You might need to adjust this based on your specific case
    }

    @Override
    public long getSize() {
        try {
            return bodyPart.getSize();
        } catch (MessagingException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public byte[] getBytes() throws IOException {
        try (InputStream inputStream = bodyPart.getInputStream()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        } catch (MessagingException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(getBytes());
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        try (OutputStream outputStream = new FileOutputStream(dest)) {
            outputStream.write(getBytes());
        }
    }
}
