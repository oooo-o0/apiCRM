package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.example.app.domain.Area;
import com.example.app.domain.Weather;

@Controller
public class WeatherController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/weather")
    public String showWeather(
            @RequestParam(name = "areaId", required = false) String areaId,
            Model model) {
        if (areaId != null) {
            String url = "http://weather.tsukumijima.net/api/forecast/city/" + areaId;
            Weather weather = restTemplate.getForObject(url, Weather.class);
            System.out.println(weather);
            model.addAttribute("weather", weather);
        }
        model.addAttribute("areaId", areaId);
        model.addAttribute("areaList", createAreaList());
        return "showWeather";
    }

    private List<Area> createAreaList() {
        List<Area> areaList = new ArrayList<>();
        areaList.add(new Area("016010", "札幌"));
        areaList.add(new Area("040010", "仙台"));
        areaList.add(new Area("130010", "東京"));
        areaList.add(new Area("270000", "大阪"));
        areaList.add(new Area("400010", "福岡"));
        areaList.add(new Area("471010", "那覇"));
        return areaList;
    }
}