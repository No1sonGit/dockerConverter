package kz.noise.converter.service;

import org.springframework.web.multipart.MultipartFile;

public interface DocxConverter {

    byte[] convertDocxToPdf (MultipartFile file);
}
