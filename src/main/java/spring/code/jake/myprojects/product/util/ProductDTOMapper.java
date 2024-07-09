package spring.code.jake.myprojects.product.util;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import spring.code.jake.myprojects.product.dto.ProductDto;
import spring.code.jake.myprojects.product.model.Product;
import spring.code.jake.myprojects.product.model.Tag;

@Service
public class ProductDTOMapper implements Function<Product, ProductDto> {
    @Override
    public ProductDto apply(Product entity) {
        return new ProductDto(entity.getProductName(),
                entity.getTags().stream().map(Tag::getTag).collect(Collectors.toList()));
    }
}
