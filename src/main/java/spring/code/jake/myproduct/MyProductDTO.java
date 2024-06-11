package spring.code.jake.myproduct;

import java.util.*;

// record 是Java 14的新特性，用于简化POJO类的定义
public record MyProductDTO(String productName, List<String> tags) {
    public MyProductDTO { // Compact Constructor for record
        tags = List.copyOf(tags); // 比Collections.unmodifiableList(tags)更好
    }
}
