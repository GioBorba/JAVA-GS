package fiap.com.br.controller;

import fiap.com.br.model.WeatherDetail;
import fiap.com.br.service.WeatherService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Inject
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeather(@QueryParam("lat") String city, @QueryParam("lon") String state) {
        try {
            WeatherDetail weatherDetail = weatherService.getWeatherByLocation(city, state);
            return Response.ok(weatherDetail).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
