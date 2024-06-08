package fiap.com.br.controller;

import fiap.com.br.model.Location;
import fiap.com.br.service.CepService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cep")
public class CepController {

    private final CepService cepService;

    @Inject
    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GET
    @Path("/{cep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocationByCep(@PathParam("cep") String cep) {
        try {
            Location location = cepService.getLocationByCep(cep);
            return Response.ok(location).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
