package com.example.app.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Forecast {
    private Date date;
    private String telop;
    private IconImage image;
}
