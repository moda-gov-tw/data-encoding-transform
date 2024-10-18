package com.fullstack.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private Integer age;
    ;
    public UserProfile() {
    }

    public UserProfile(String name, String phone, Integer age) {
        this.name = name;
        this.phone = phone;
        this.age = age;
    }
}
