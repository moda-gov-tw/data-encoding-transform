package com.fullstack.backend.service;

import com.fullstack.backend.model.ListResponse;
import com.fullstack.backend.model.UserProfile;
import com.fullstack.backend.repository.UserRepository;
import com.fullstack.backend.util.ImportDataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Override
    public ListResponse listUsersByFile() {
        List<UserProfile> users = repository.findAll();
        ListResponse response = new ListResponse();
        response.setData(users);
        response.setTotalCount(users.size());
        return response;
    }

    @Override
    public void importByRowData(InputStream is) {
        List<UserProfile> users = new ArrayList<>();
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                Stream<String> lines = reader.lines();
                lines.map(this::mapLineToUserProfile)
                        .forEach(user -> {
                            users.add(user);
                        });
            }
        } catch (IOException e) {
            logger.error("Error reading User.data", e);
            throw new RuntimeException("Error reading User.data", e);
        }
        repository.saveAll(users);
    }

    private UserProfile mapLineToUserProfile(String line) {
        Map<String, String> userFields = ImportDataUtils.getAllField("USER", line);
        String name = Optional.ofNullable(userFields.get("NAME")).orElse("Unknown");
        String pno = Optional.ofNullable(userFields.get("PNO")).orElse("Unknown");
        int age = Optional.ofNullable(userFields.get("AGE"))
                .map(ageStr -> {
                    try {
                        return Integer.parseInt(ageStr);
                    } catch (NumberFormatException e) {
                        logger.warn("Invalid age format for user: {}", name);
                        return 0;
                    }
                })
                .orElse(0);
        return new UserProfile(name, pno, age);
    }
}
