@Entity
@Table(name = "product_raw_materials")
public class ProductRawMaterial extends PanacheEntity{

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    public Product product;

    @ManyToOne
    @JoinColumn(name = "raw_material_id", nullable = false)
    public RawMaterial rawMaterial;

    @Column(nullable = false)
    public Integer requiredQuantity;
}