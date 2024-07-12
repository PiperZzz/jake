package spring.code.jake.myprojects.product.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

// record 是Java 14的新特性，用于简化POJO类的定义
public record ProductDto(
        @NotBlank(message = "Product name is required") String productName,
        List<String> tags) {
    public ProductDto { // Compact Constructor for record
        tags = List.copyOf(tags); // 比Collections.unmodifiableList(tags)更好
    }
}
