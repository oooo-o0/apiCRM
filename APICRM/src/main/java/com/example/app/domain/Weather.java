package com.example.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Weather {
    private String title;
    private Forecast[] forecasts;
}
