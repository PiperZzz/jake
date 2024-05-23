package spring.code.jake.myproduct;

import java.util.*;

public record MyProductDTO(String productName, List<String> tags) {
    public MyProductDTO { // Compact Constructor for record
        tags = Collections.unmodifiableList(tags);
    }
}
