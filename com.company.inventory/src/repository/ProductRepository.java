@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product>{

    public List<Product> findAllOrderByPriceDesc() {
        return list("ORDER BY price DESC");
    }
}