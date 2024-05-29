package spring.code.jake.myproduct;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.*;

public class MyProductServiceTests {

    @Mock
    private MyProductsRepository myProductsRepository;

    @Mock
    private MyProductDTOMapper myProductDTOMapper;

    @InjectMocks
    private MyProductService myProductService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateProductById() {
        Long productId = 1L;
        MyProductDTO productDTO = new MyProductDTO("UpdatedProduct", new ArrayList<>());
        MyProductEntity productEntity = new MyProductEntity();
        productEntity.setProductName("OldProduct");

        when(myProductsRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(myProductsRepository.save(any(MyProductEntity.class))).thenReturn(productEntity);
        when(myProductDTOMapper.apply(any(MyProductEntity.class))).thenReturn(productDTO); 

        MyProductDTO updatedProductDTO = myProductService.updateProductById(productId, productDTO);

        assertEquals("UpdatedProduct", updatedProductDTO.productName());
        verify(myProductsRepository).save(productEntity);
        verify(myProductDTOMapper).apply(productEntity);
    }

    @Test
    public void testUpdateProductById_ProductNotFound() {
        Long productId = 1L;
        MyProductDTO productDTO = new MyProductDTO("UpdatedProduct", new ArrayList<>());

        when(myProductsRepository.findById(productId)).thenReturn(Optional.empty());

        MyProductException exception = assertThrows(MyProductException.class, () -> {
            myProductService.updateProductById(productId, productDTO);
        });

        assertEquals("The Product to update does not exist", exception.getMessage());
        verify(myProductsRepository, never()).save(any(MyProductEntity.class));
    }

    @Test
    public void testGetProductsByName() {
        String keyword = "Test";
        int page = 0;
        int size = 10;
        MyProductEntity productEntity = new MyProductEntity();
        MyProductDTO productDTO = new MyProductDTO("TestProduct", new ArrayList<>());

        when(myProductsRepository.findByProductNameContaining(eq(keyword), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(productEntity)));
        when(myProductDTOMapper.apply(any(MyProductEntity.class))).thenReturn(productDTO);

        List<MyProductDTO> products = myProductService.getProductsByName(keyword, page, size);

        assertEquals(1, products.size());
        assertEquals("TestProduct", products.get(0).productName());
        verify(myProductsRepository).findByProductNameContaining(eq(keyword), any(Pageable.class));
        verify(myProductDTOMapper).apply(productEntity);
    }
}

