@Entity
@Table(name = "products")
public class Product extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String code;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public BigDecimal price;
}