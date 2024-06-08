package fiap.com.br.service;

import fiap.com.br.model.WeatherDetail;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

@Singleton
public class WeatherService {

    private final Client client;
    private final String apiKey = "38f6e05864e0378ddd78790432e688ea";

    @Inject
    public WeatherService() {
        this.client = ClientBuilder.newClient();
    }

    public WeatherDetail getWeatherByLocation(String city, String state) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s,%s&appid=%s&units=metric", city, state, apiKey);
        WebTarget target = client.target(url);
        return target.request(MediaType.APPLICATION_JSON).get(WeatherDetail.class);
    }
}
