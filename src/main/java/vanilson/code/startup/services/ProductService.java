package vanilson.code.startup.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vanilson.code.startup.dto.ProductDTO;
import vanilson.code.startup.exception.ProductNotFoundException;
import vanilson.code.startup.exception.ProductNullableException;
import vanilson.code.startup.persistence.model.Product;
import vanilson.code.startup.persistence.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            logger.info(" founded the product with id {}", id);
            return product.get();
        }
        throw new ProductNotFoundException(" Product with id " + id + " not found");
    }

    public Product createProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            logger.error(" product is null {}", (Object) null);
            throw new ProductNullableException(" product can't be null");
        }
        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();

        logger.info("Product save with success {}", product);
        return productRepository.save(product);

    }
}
