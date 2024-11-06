package com.fullstack.backend.service;

import com.fullstack.backend.model.ListResponse;

import java.io.InputStream;

public interface UserService {
    ListResponse listUsersByFile();

    void importByRowData(InputStream is);
}
