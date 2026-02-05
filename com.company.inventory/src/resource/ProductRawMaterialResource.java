@Path("/product-raw-materials")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APLLICATION_JSON)
public class ProductRawMaterialResource {

    @Inject
    ProductRawMaterialService service;

    @POST
    @Transactinal
    public ProductRawMaterial create(ProductRawMaterialDTO dto) {
        return service.associate(dto);
    }

    @GET
    @Path("/product/{productId}")
    public List<ProductRawMaterial> listByProduct(@PathParam("productId") Long productId) {
        return service.findByProduct(productId);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }
}