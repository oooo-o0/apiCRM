package com.example.app.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.app.domain.ZipInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/search")
public class ZipcodeController {

    @GetMapping
    public String searchGet() {
        return "search";
    }

    @PostMapping
    public String searchPost(
            @RequestParam String zipcode,
            Model model) throws IOException, InterruptedException {
        // エンドポイント + 送信する郵便番号
        String uri = "https://zipcloud.ibsnet.co.jp/api/search?zipcode=" +
                zipcode;
        // リクエストの準備
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(URI.create(uri)).build();
        // リクエストを送り、結果をJSON 文字列として取得
        HttpResponse<String> response = client.send(request,
                BodyHandlers.ofString());
        System.out.println(response.body());
        // JSON 文字列 ⇒ ZipInfo オブジェクト
        ObjectMapper mapper = new ObjectMapper();
        ZipInfo info = mapper.readValue(response.body(), ZipInfo.class);
        model.addAttribute("info", info);
        return "search";
    }
}
