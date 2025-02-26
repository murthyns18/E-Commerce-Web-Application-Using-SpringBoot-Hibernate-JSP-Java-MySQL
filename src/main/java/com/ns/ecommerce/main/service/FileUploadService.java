package com.ns.ecommerce.main.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService 
{

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    public String uploadImage(MultipartFile file) throws IOException 
    {
        if (file.isEmpty())
        {
            throw new IOException("File is empty!");
        }

        String fileName = file.getOriginalFilename();
        java.nio.file.Path path = Paths.get(UPLOAD_DIR + fileName); 
        Files.createDirectories(path.getParent());  
        Files.write(path, file.getBytes());  

        return fileName; 
    }
}