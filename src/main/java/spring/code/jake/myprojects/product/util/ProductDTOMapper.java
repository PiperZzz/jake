package spring.code.jake.myprojects.product.util;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import spring.code.jake.myprojects.product.dto.ProductDTO;
import spring.code.jake.myprojects.product.model.Product;
import spring.code.jake.myprojects.product.model.Tag;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product entity) {
        return new ProductDTO(entity.getProductName(),
                entity.getTags().stream().map(Tag::getTag).collect(Collectors.toList()));
    }
}
