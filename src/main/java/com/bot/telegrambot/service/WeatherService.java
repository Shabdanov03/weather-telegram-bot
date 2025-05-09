package com.bot.telegrambot.service;

import com.bot.telegrambot.dto.WeatherResponse;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Setter
public class WeatherService {
    @Value("${weather.api-key}")
    private String  API_KEY;

    @Value("${weather.base.daily-url}")
    private String  BASE_DAILY_URL;

    @Value("${weather.base.hourly-url}")
    private String BASE_HOURLY_URL;

    @PostConstruct
    public void init() {
        System.out.println("API_KEY = " + API_KEY);
        System.out.println("BASE_DAILY_URL = " + BASE_DAILY_URL);
        System.out.println("BASE_HOURLY_URL = " + BASE_HOURLY_URL);
    }


    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getHoursByCityName(String cityName) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_HOURLY_URL)
                .queryParam("city",cityName)
                .queryParam("key",API_KEY)
                .queryParam("hours",24)
                .toUriString();
        return restTemplate.getForObject(url, WeatherResponse.class);
    }

    public WeatherResponse getDaysByCityName(String cityName) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_DAILY_URL)
                .queryParam("city",cityName)
                .queryParam("key",API_KEY)
                .queryParam("days",7)
                .toUriString();
        return restTemplate.getForObject(url, WeatherResponse.class);    }
}
