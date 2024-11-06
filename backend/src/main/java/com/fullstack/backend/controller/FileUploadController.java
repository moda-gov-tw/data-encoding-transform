package com.fullstack.backend.controller;

import com.fullstack.backend.repository.UserRepository;
import com.fullstack.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FileUploadController {

    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        StringBuilder content = new StringBuilder();
        userService.importByRowData(file.getInputStream());
        return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.OK);
    }
}
