package com.subham.smartsearch.controller;

import com.subham.smartsearch.model.DocumentEntity;
import com.subham.smartsearch.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@RestController
public class FileController {

    @Autowired
    private DocumentRepository repository;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        String uploadDir = "uploads/";

        Path path = Paths.get(uploadDir + file.getOriginalFilename());

        Files.copy(
                file.getInputStream(),
                path,
                StandardCopyOption.REPLACE_EXISTING
        );

        DocumentEntity document = new DocumentEntity();

        document.setFileName(file.getOriginalFilename());
        document.setFilePath(path.toString());
        document.setUploadedAt(LocalDateTime.now());

        repository.save(document);

        return "File uploaded successfully";
    }
}