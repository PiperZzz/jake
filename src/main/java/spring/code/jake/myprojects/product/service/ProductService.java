package spring.code.jake.myprojects.product.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;
import spring.code.jake.myprojects.product.dto.ProductDto;
import spring.code.jake.myprojects.product.exception.ProductException;
import spring.code.jake.myprojects.product.model.Product;
import spring.code.jake.myprojects.product.repository.ProductRepository;
import spring.code.jake.myprojects.product.util.ProductDTOMapper;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    @Cacheable("myProducts")
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("The Product does not exist"));
        return productDTOMapper.apply(product);
    }

    @Transactional
    @CacheEvict(value = "myProducts", key = "#productId")
    public ProductDto updateProductById(long productId, ProductDto productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("The Product to update does not exist"));
        product.setProductName(productDTO.productName());
        productRepository.save(product);
        logger.info("Product with id {} and name {} updated successfully", productId, product.getProductName());
        return productDTOMapper.apply(product);
    }

    @Cacheable("myProductLists")
    public List<ProductDto> getProductsByName(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByProductNameContaining(keyword, pageable)
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }
}
