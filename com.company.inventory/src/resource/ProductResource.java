@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService service;

    @GET
    public List<Product> list() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Product find(@PathParam("id") Long id){
        return service.findById(id);
    }

    @POST
    @Transactional
    public Product create(Product product) {
        return service.save(product);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Product update(@PathParam("id") Long id, Product product) {
        return service.update(id, product);
    }

    @DELETE
    @Path("/{id}")
    @Transational
    public void delete(@PathParam("id") Long id){
        service.delete(id);
    }
}