package vn.hecquyn.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hecquyn.laptopshop.domain.Product;
import vn.hecquyn.laptopshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product pr) {
        this.productRepository.save(pr);
    }

    public List<Product> fetchProducts() {
        return this.productRepository.findAll();
    }
}
