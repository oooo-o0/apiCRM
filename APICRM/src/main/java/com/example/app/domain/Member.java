package com.example.app.domain;

import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Member {
    private Integer id;
    @NotBlank
    @Size(max = 10)
    private String name;
    @Min(0)
    @Max(150)
    private Integer age;
    @Size(max = 255)
    private String address;
    private Integer typeId;
    private Date created;
}