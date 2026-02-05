@ApplicationScoped
public class ProductRawMaterialRepository implements PanacheRepository<ProductRawMaterial>{

    public List<ProductRawMaterial> findByProduct(Product product){
        return list("product", product);
    }
}