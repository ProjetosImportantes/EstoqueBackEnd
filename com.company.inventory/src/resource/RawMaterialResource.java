@Path("/raw-materials")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RawMaterialResource {

    @Inject
    RawMaterialService service;

    @GET
    public List<RawMaterial> list() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public RawMaterial find(@PathParam("id") Long id) {
        return service.fingById(id);
    }

    @POST
    @transactional
    public RawMaterial create(RawMaterial rawMaterial) {
        return service.save(rawMaterial);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public RawMaterial update(@PathParam("id") Long id, RawMaterial rawMaterial) {
        return service.update(id, rawMaterial);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }
}