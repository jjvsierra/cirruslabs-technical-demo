package com.cirruslabs.technical.service;

import com.cirruslabs.technical.model.GeolocationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

import static com.cirruslabs.technical.utils.Constants.CANADA;
import static com.cirruslabs.technical.utils.Constants.USER_NOT_ELIGIBLE_ERROR_MESSAGE;
import static com.cirruslabs.technical.utils.Constants.WELCOME_CANADA_MESSAGE;

@Service
public class GeolocationService {
    @Value("${ip-api.geolocation.url}")
    private String baseIpApiUrl;
    private final WebClient webClient;

    public GeolocationService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String validateCanadaLocation(String ipAddress, String userName) {
        GeolocationDto geolocation = getGeolocationByIp(ipAddress);
        if (Objects.nonNull(geolocation.getCountry()) && geolocation.getCountry().equalsIgnoreCase(CANADA)) {
            return String.format(WELCOME_CANADA_MESSAGE, userName, geolocation.getCity());
        }
        return USER_NOT_ELIGIBLE_ERROR_MESSAGE;
    }

    private GeolocationDto getGeolocationByIp(String ipAddress) {
        return webClient.get()
                .uri(baseIpApiUrl + ipAddress)
                .retrieve()
                .bodyToMono(GeolocationDto.class)
                .block();
    }

}
