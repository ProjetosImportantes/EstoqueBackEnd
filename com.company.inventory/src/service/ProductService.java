@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository repository;

    public List<Product> findAll(){
        return repository.listAll();
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }

    public Product save(Product product) {
        repository.persist(product);
        return product;
    }

    public Product update(Long id, Product data){
        Product product = findById(id);
        product.name = data.name;
        product.price = data.price;
        product.code = data.code;
        return product;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}