package com.fullstack.backend.controller;

import com.fullstack.backend.repository.UserRepository;
import com.fullstack.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FileUploadController {

    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return buildErrorResponse("File is empty", HttpStatus.BAD_REQUEST);
        }
        String safeFilename = getSafeFilename(file);
        if (safeFilename.isBlank()) {
            return buildErrorResponse("File name is invalid", HttpStatus.BAD_REQUEST);
        }
        try {
            userService.importByRowData(file.getInputStream());
        } catch (IOException e) {
            return buildErrorResponse("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(safeFilename, HttpStatus.OK);
    }

    private ResponseEntity<String> buildErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }

    private String getSafeFilename(MultipartFile file) {
        return HtmlUtils.htmlEscape(Objects.requireNonNull(file.getOriginalFilename()));
    }

}
