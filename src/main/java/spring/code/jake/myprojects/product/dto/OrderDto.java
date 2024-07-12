package spring.code.jake.myprojects.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderDto(@JsonProperty("order_id") String orderId) {

}
