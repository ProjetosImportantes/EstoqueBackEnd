@ApplicationScoped
public class ProductionService {

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductRawMaterialRepository productRawMaterialRepository;

    @Inject
    RawMaterialRepository rawMaterialRepository;

    public ProductionResponseDTO calculateProduction() {

        List<Product> products = productRepository.findAllOrderByPriceDesc();

        Map<Long, Integer> virtualStock = rawMaterialRepository
                .listAll()
                .stream()
                .collect(Collectors.toMap(
                        rn -> rm.id,
                        rm -> rm.stockQuantity
                ));

        List<ProductionItemDTO> result = new ArrayList<>();
        BigDecimal totalProductionValue = BigDecimal.ZERO;

        for (Product product : products) {
            List<ProductRawMaterial> material = productRawMaterialRepository.findByProduct(product);

            if (material.isEmpty()) continue;

            Integer maxProduction = Integer.MAX_VALUE;

            for (ProductRawMaterial prmm : material) {
                Integer available = virtualStock.get(prm.rawMaterial.id);
                Integer possible = available / prm.requiredQuantity;

                maxProduction = Math.min(maxProduction, possible);
            }

            if (maxProduction <= 0) continue;

            for (ProductRawMaterial prm : materials) {
                Long rmId = prm.rawMaterial.id;
                Integer newStock = virtualStock.get(rmId) - (prm.requiredQuantity * maxProduction);
                virtualStock.put(rmId, newStock);
            }

            ProductionItemDTO item = new ProductionItemDTO();
            item.productId = product.id;
            item.productName = product.name;
            item.quantity = maxProduction;
            item.unitPrice = product.price;
            item.totalValue = product.price.multiply(BigDecimal.valueOf(maxProduction));

            result.add(item);
            totalProductionValue = totalProductionValue.add(item.totalValue);
        }

        ProductionResponseDTO response = new ProductionResponseDTO();
        response.items = result;
        response.totalProductionValue = totalProductionValue;

        return response;
    }
}