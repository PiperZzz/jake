package spring.code.jake.myproduct;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import jakarta.validation.constraints.NotBlank;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.slf4j.*;

@Service
public class MyProductService {

    private static final Logger logger = LoggerFactory.getLogger(MyProductService.class);

    private final MyProductsRepository myProductsRepository;
    private final MyProductDTOMapper myProductDTOMapper;

    public MyProductService(MyProductsRepository myProductsRepository, MyProductDTOMapper myProductDTOMapper) {
        this.myProductsRepository = myProductsRepository;
        this.myProductDTOMapper = myProductDTOMapper;
    }

    @Transactional
    public MyProductDTO updateProductById(@NotBlank Long productId, MyProductDTO productDTO) {
        MyProductEntity product = myProductsRepository.findById(productId)
                .orElseThrow(() -> new MyProductException("The Product to update does not exist"));
        product.setProductName(productDTO.productName());
        myProductsRepository.save(product);
        logger.info("Product with id {} and name {} updated successfully", productId, product.getProductName());
        return myProductDTOMapper.apply(product);
    }

    // public List<MyProductDTO> getProductsByName(int page, int size) {
    //     Pageable pageable = PageRequest.of(page, size);
    //     return myProductsRepository.findProductsByName(pageable)
    //             .stream()
    //             .map(myProductDTOMapper)
    //             .collect(Collectors.toList());
    // }
}
    // TODO: MyProductDtoMapper
