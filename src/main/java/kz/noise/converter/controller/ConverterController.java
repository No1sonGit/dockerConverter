package kz.noise.converter.controller;

import kz.noise.converter.service.DocxConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("api/v1")
public class ConverterController {

    private final DocxConverter converter;

    @Autowired
    public ConverterController(DocxConverter converter) {
        this.converter = converter;
    }

    @PostMapping(value = "/convertDocxToPdf", consumes = MediaType.ALL_VALUE)
    public byte[] convertDocxToPdf(@RequestBody MultipartFile file) {
        return converter.convertDocxToPdf(file);
    }

}
