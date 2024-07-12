package spring.code.jake.myprojects.product.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import spring.code.jake.myprojects.product.dto.ProductDto;
import spring.code.jake.myprojects.product.exception.ProductException;
import spring.code.jake.myprojects.product.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @GetMapping("/{product-name}/orders/{order-id}")
    public ResponseEntity<String> getProductOrder(@PathVariable("product-name") String productName,
            @PathVariable("order-id") int orderId) {
        // Path Param 适合有固定层级嵌套的参数。例如，/products/product123/orders/order234，这符合RESTful架构风格
        // URI路径内的变量名连字符用(-)
        return ResponseEntity.ok(productName + orderId + "OK");
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page-number", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page-size", defaultValue = "10") int pageSize) {
        // Query Param 适合多个层级相对平行的动态参数，比如过滤、排序、分页。 
        // 分页Pagination的参数，page-number和page-size通常是要求同时存在的层级平行的参数，而keyword是可选的动态参数 /products/?keyword=something&page-number=1&page-size=10
        // 敏感数据不能用Query Param，必须用Request Body，因为Query Param依然会暴露在URI中
        // URI内Query Param变量名的连字符用(-)    
        List<ProductDto> products = productService.getProductsByName(keyword, pageNumber, pageSize);

        if (products == null) {
            throw new ProductException("More Specific Reason Here"); // 交给RestControllerAdvice异常处理器去处理是最佳实践
        } else if (products.isEmpty()) { 
            // 这里只做演示用，实际开发中空列表也是合法的200响应，不应该抛出异常，代表没有符合搜索条件的结果
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"); // 实际开发中不推荐，这里只做演示，应该像上面一样
        }

        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{userName}/order/{orderId}")
    public ResponseEntity<String> deleteUserOrder(@PathVariable("userName") String userName,
            @PathVariable("orderId") int orderId) {
        // Path Parameter 的 DELETE 逻辑几乎与 GET 的完全一样，只是 HTTP 方法不同
        return ResponseEntity.noContent().build();
        // 204 请求成功，但没有内容返回（通常用于删除操作）
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam("userName") String userName,
            @RequestParam(value = "force", defaultValue = "false") boolean force) {
        // Query Parameter 适用于有条件参数的情况，这类似于 GET 方法处理分页和过滤的条件参数
        if (force) {
            // 执行强制删除逻辑
        } else {
            // 执行普通删除逻辑
        }
        return ResponseEntity.ok(userName + " deleted, force=" + force);
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        // 使用 RequestBody 是 POST 方法最常用的方式，也是现代 Web API 开发中常用的方式
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        // 201 Resource Created
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        // 使用 RequestBody 是 PUT 方法最常用的方式
        // 通常会结合 Path Parameter 或 Query Parameter 先获取要更新的数据实体
        return ResponseEntity.ok("User updated successfully");
    }

    record User(String userName, String password) {
    }
}
