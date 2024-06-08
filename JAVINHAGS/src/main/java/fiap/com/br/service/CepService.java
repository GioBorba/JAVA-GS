package fiap.com.br.service;

import fiap.com.br.exception.InvalidCepException;
import fiap.com.br.model.Location;
import lombok.Getter;
import lombok.Setter;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Singleton
public class CepService {

    private final Client client;

    @Inject
    public CepService() {
        this.client = ClientBuilder.newClient();
    }

    public Location getLocationByCep(String cep) {
        if (!isValidCep(cep)) {
            throw new InvalidCepException("Invalid CEP format");
        }

        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);
        WebTarget target = client.target(url);

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new InvalidCepException("Invalid CEP");
        }

        ViaCepResponse viaCepResponse = response.readEntity(ViaCepResponse.class);

        if (viaCepResponse == null || viaCepResponse.getLocalidade() == null || viaCepResponse.getUf() == null) {
            throw new InvalidCepException("Invalid CEP");
        }

        Location location = new Location();
        location.setCity(viaCepResponse.getLocalidade());
        location.setState(viaCepResponse.getUf());
        return location;
    }

    private boolean isValidCep(String cep) {
        return cep.matches("\\d{5}-?\\d{3}");
    }


    @Setter
    @Getter
    private static class ViaCepResponse {
        private String localidade;
        private String uf;

    }
}
