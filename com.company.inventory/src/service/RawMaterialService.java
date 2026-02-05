@ApplicationScoped
public class RawMaterialService {

    @Inject
    RawMaterialRepository repository;

    public List<RawMaterial> findAll() {
        return repository.listAll();
    }

    public RawMaterial findById(Long id) {
        return repository.findById(id);
    }

    public RawMaterial save(RawMaterial rawMaterial) {
        repository.persist(rawMaterial);
        return rawMaterial;
    }

    public RawMaterial update(Long id, RawMaterial data) {
        RawMaterial material = findById(id);
        material.code = data.code;
        material.name = data.name;
        material.stockQuantity = data.stockQuantity;
        return material;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}