package spring.code.jake.myproduct;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import spring.code.jake.myprojects.product.dto.ProductDto;
import spring.code.jake.myprojects.product.exception.ProductException;
import spring.code.jake.myprojects.product.model.Product;
import spring.code.jake.myprojects.product.repository.ProductRepository;
import spring.code.jake.myprojects.product.service.ProductService;
import spring.code.jake.myprojects.product.util.ProductDTOMapper;

import java.util.*;

@SpringBootTest
public class MyProductServiceTests {

    @Mock
    private ProductRepository myProductsRepository;

    @Mock
    private ProductDTOMapper myProductDTOMapper;

    @InjectMocks
    private ProductService myProductService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateProductById() {
        Long productId = 1L;
        ProductDto productDTO = new ProductDto("UpdatedProduct", new ArrayList<>());
        Product productEntity = new Product();
        productEntity.setProductName("OldProduct");

        when(myProductsRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(myProductsRepository.save(any(Product.class))).thenReturn(productEntity);
        when(myProductDTOMapper.apply(any(Product.class))).thenReturn(productDTO);

        ProductDto updatedProductDTO = myProductService.updateProductById(productId, productDTO);

        assertEquals("UpdatedProduct", updatedProductDTO.productName());
        verify(myProductsRepository).save(productEntity);
        verify(myProductDTOMapper).apply(productEntity);
    }

    @Test
    public void testUpdateProductById_ProductNotFound() {
        Long productId = 1L;
        ProductDto productDTO = new ProductDto("UpdatedProduct", new ArrayList<>());

        when(myProductsRepository.findById(productId)).thenReturn(Optional.empty());

        ProductException exception = assertThrows(ProductException.class, () -> {
            myProductService.updateProductById(productId, productDTO);
        });

        assertEquals("The Product to update does not exist", exception.getMessage());
        verify(myProductsRepository, never()).save(any(Product.class));
    }

    @Test
    public void testGetProductsByName() {
        String keyword = "Test";
        int page = 0;
        int size = 10;
        Product productEntity = new Product();
        ProductDto productDTO = new ProductDto("TestProduct", new ArrayList<>());

        when(myProductsRepository.findByProductNameContaining(eq(keyword), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(productEntity)));
        when(myProductDTOMapper.apply(any(Product.class))).thenReturn(productDTO);

        List<ProductDto> products = myProductService.getProductsByName(keyword, page, size);

        assertEquals(1, products.size());
        assertEquals("TestProduct", products.get(0).productName());
        verify(myProductsRepository).findByProductNameContaining(eq(keyword), any(Pageable.class));
        verify(myProductDTOMapper).apply(productEntity);
    }
}
