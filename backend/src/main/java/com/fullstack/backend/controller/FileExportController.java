package com.fullstack.backend.controller;

import com.fullstack.backend.model.UserProfile;
import com.fullstack.backend.repository.UserRepository;
import com.fullstack.backend.service.UserService;
import com.fullstack.backend.util.FillerUtils;
import com.fullstack.backend.util.ResourceBundler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FileExportController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    @PostMapping("/fileExport")
    public ResponseEntity<InputStreamResource> handleFileExport(
            @RequestParam String alignment,
            @RequestParam String filled,
            @RequestParam String charset
    ) {

        String fileName = "userInfo.txt";
        Path filePath = Path.of(fileName); // 獲取文件的Path對象

        try {
            // 取資料
            List<UserProfile> users = repository.findAll();
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 沒有資料時返回 204
            }

            // 準備寫入文件
            StringBuilder dataBuilder = new StringBuilder();
            // 處理資料
            for (UserProfile userProfile : users) {

                String name = fillString(userProfile.getName(), "USER.NAME", alignment, filled, charset);
                String pno = fillString(userProfile.getPno(), "USER.PNO", alignment, filled, charset);
                String age = fillString(String.valueOf(userProfile.getAge()), "USER.AGE", alignment, filled, charset);

                dataBuilder.append(name).append(pno).append(age).append(System.lineSeparator());
            }

            // 寫入文件
            Files.writeString(filePath, dataBuilder.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // 準備文件下載
            return createFileResponse(filePath);

        } catch (IOException e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile() {

        Path filePath = Path.of("userInfo.txt");

        if (!Files.exists(filePath)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 檔案不存在時返回 404

        }

        return createFileResponse(filePath);
    }

    //創建文件
    private ResponseEntity<InputStreamResource> createFileResponse(Path filePath) {

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath.toFile());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath.getFileName() + "\"");
            headers.setContentType(MediaType.TEXT_PLAIN);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(Files.size(filePath))
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(fileInputStream));

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String fillString(String value, String configKey, String alignment, String filled, String charset) {
        String[] lengthConfig = ResourceBundler.getProps("config", configKey).split(",");
        Charset chosenCharset = StandardCharsets.UTF_8;

        // 對齊位置轉換
        String userAlignment = StringUtils.equals("靠右", alignment) ? "left" : "right";

        // 轉換編碼
        if ("BIG5".equals(charset)) {
            chosenCharset = Charset.forName("BIG5");
        }
        return FillerUtils.stringFiller(value, Integer.parseInt(lengthConfig[1]), userAlignment, filled, chosenCharset);
    }
}
