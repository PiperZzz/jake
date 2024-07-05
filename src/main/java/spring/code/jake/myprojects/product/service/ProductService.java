package spring.code.jake.myprojects.product.service;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.slf4j.*;

import lombok.AllArgsConstructor;
import spring.code.jake.myprojects.product.dao.ProductsRepository;
import spring.code.jake.myprojects.product.dto.ProductDTO;
import spring.code.jake.myprojects.product.exception.ProductException;
import spring.code.jake.myprojects.product.model.Product;
import spring.code.jake.myprojects.product.util.ProductDTOMapper;

@Service
@AllArgsConstructor
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductsRepository productsRepository;
    private final ProductDTOMapper productDTOMapper;

    @Cacheable("myProducts")
    public ProductDTO getProductById(Long productId) {
        Product product = productsRepository.findById(productId)
                .orElseThrow(() -> new ProductException("The Product does not exist"));
        return productDTOMapper.apply(product);
    }

    @Transactional
    @CacheEvict(value = "myProducts", key = "#productId")
    public ProductDTO updateProductById(long productId, ProductDTO productDTO) {
        Product product = productsRepository.findById(productId)
                .orElseThrow(() -> new ProductException("The Product to update does not exist"));
        product.setProductName(productDTO.productName());
        productsRepository.save(product);
        logger.info("Product with id {} and name {} updated successfully", productId, product.getProductName());
        return productDTOMapper.apply(product);
    }

    @Cacheable("myProductLists")
    public List<ProductDTO> getProductsByName(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productsRepository.findByProductNameContaining(keyword, pageable)
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }
}
