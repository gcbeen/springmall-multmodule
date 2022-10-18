package com.gcbeen.springmalladmin.model;

import lombok.Data;

@Deprecated
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
