package com.example.app.domain;

import lombok.Data;

@Data
public class ZipInfo {
    private String message;
    private Result[] results;
    private Integer status;
}
