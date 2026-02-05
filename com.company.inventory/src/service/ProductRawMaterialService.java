@ApplicationScoped
public class ProductRawMaterialService {

    @Inject
    ProductRepository productRepository;

    @Inject
    RawMaterialRepository rawMaterialRepository;

    @Inject
    ProductRawMaterialRepository repository;

    public ProductRawMaterial associate(ProductRawMaterialDTO dto) {

        Product product = productRepository.findById(dto.productID);
        RawMaterial rawMaterial = rawMaterialRepository.findById(dto.rawMaterial);

        ProductRawMaterial prm = new ProductRawMaterial();
        prm.product = product;
        prm.rawMaterial = rawMaterial;
        prm.requiredQuantity = dto.requiredQuantity;

        repository.persist(prm);
        return prm;
    }

    public List<ProductRawMaterial> findByProduct(Long productId) {
        Product product = productRepository.findById(productId);
        return repository.findByProduct(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}