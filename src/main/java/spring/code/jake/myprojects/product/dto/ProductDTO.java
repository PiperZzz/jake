package spring.code.jake.myprojects.product.dto;

import java.util.*;

// record 是Java 14的新特性，用于简化POJO类的定义
public record ProductDTO(String productName, List<String> tags) {
    public ProductDTO { // Compact Constructor for record
        tags = List.copyOf(tags); // 比Collections.unmodifiableList(tags)更好
    }
}
