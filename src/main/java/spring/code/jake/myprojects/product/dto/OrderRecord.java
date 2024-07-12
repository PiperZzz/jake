package spring.code.jake.myprojects.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderRecord(
        @JsonProperty("order_id") String orderId,

        String productName,

        String orderDate,

        String orderStatus) {
}
