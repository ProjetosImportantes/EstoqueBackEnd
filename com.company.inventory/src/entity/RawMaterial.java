@Entity
@Table(name = "raw_material")
public class RawMaterial extends PanacheEntity{

    @Column(nullable = false, unique = true)
    public String code;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public Integer stockQuantity;
}