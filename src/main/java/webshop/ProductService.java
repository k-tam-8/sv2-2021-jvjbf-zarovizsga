package webshop;

public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saleProduct(long id, int amount) {
        if (productRepository.getStockFromProductId(id) >= amount) {
            productRepository.updateProductStock(id, amount);
        } else throw new IllegalArgumentException("Not enough product at stock.");
    }
}
