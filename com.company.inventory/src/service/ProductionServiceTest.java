@QuarkusTest
public class ProductionServiceTest {

    @Inject
    ProductionService productionService;

    @InjectMock
    ProductRepository productRepository;

    @InjectMock
    ProductRawMaterialRepository productRawMaterialRepository;

    @InjectMock
    RawMaterialRepository rawMaterialRepository;

    @Test
    void shouldCalculateProductionCorrectly() {

        // Produto
        Product product = new Product();
        product.id = 1L;
        product.name = "Product A";
        product.price = BigDecimal.valueOf(100);

        // Matéria-prima
        RawMaterial material = new RawMaterial();
        material.id = 10L;
        material.stockQuantity = 10;

        // Associação
        ProductRawMaterial prm = new ProductRawMaterial();
        prm.product = product;
        prm.rawMaterial = material;
        prm.requiredQuantity = 2;

        // Mocks
        Mockito.when(productRepository.findAllOrderByPriceDesc())
                .thenReturn(List.of(product));

        Mockito.when(rawMaterialRepository.listAll())
                .thenReturn(List.of(material));

        Mockito.when(productRawMaterialRepository.findByProduct(product))
                .thenReturn(List.of(prm));

        // Execução
        ProductionResponseDTO response =
                productionService.calculateProduction();

        // Validações
        assertEquals(1, response.items.size());
        assertEquals(5, response.items.get(0).quantity);
        assertEquals(BigDecimal.valueOf(500), response.totalProductionValue);
    }
}
