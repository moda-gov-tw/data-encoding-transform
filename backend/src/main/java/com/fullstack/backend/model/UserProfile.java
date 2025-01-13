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
    private String pno;
    private Integer age;
    ;
    public UserProfile() {
    }

    public UserProfile(String name, String pno, Integer age) {
        this.name = name;
        this.pno = pno;
        this.age = age;
    }
}
