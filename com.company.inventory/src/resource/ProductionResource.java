@Path("/production")
@Produces(MediaType.APPLICATION_JSON)
public class ProductionResource {

    @Inject
    ProductionService service;

    @GET
    @Path("/seggestion")
    public ProductionResponseDTO suggestProduction(){
        return service.calculateProduction();
    }
}