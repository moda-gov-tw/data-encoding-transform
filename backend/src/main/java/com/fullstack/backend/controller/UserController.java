package com.fullstack.backend.controller;

import com.fullstack.backend.model.ListResponse;
import com.fullstack.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listByFile")
    public ListResponse listUsersByFile() {
        return userService.listUsersByFile();
    }
}
