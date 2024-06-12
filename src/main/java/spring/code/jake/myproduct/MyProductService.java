package spring.code.jake.myproduct;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import jakarta.validation.constraints.*;
import jakarta.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.slf4j.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyProductService {

    private static final Logger logger = LoggerFactory.getLogger(MyProductService.class);

    private final MyProductsRepository myProductsRepository;
    private final MyProductDTOMapper myProductDTOMapper;

    @Cacheable("myProducts")
    public MyProductDTO getProductById(Long productId) {
        MyProductEntity product = myProductsRepository.findById(productId)
                .orElseThrow(() -> new MyProductException("The Product does not exist"));
        return myProductDTOMapper.apply(product);
    }

    @Transactional
    @CacheEvict(value = "myProducts", key = "#productId")
    public MyProductDTO updateProductById(@NotBlank Long productId, @NotNull MyProductDTO productDTO) {
        MyProductEntity product = myProductsRepository.findById(productId)
                .orElseThrow(() -> new MyProductException("The Product to update does not exist"));
        product.setProductName(productDTO.productName());
        myProductsRepository.save(product);
        logger.info("Product with id {} and name {} updated successfully", productId, product.getProductName());
        return myProductDTOMapper.apply(product);
    }

    @Cacheable("myProductLists")
    public List<MyProductDTO> getProductsByName(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return myProductsRepository.findByProductNameContaining(keyword, pageable)
                .stream()
                .map(myProductDTOMapper)
                .collect(Collectors.toList());
    }
}
