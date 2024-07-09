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

    @GetMapping("/user")
    public ResponseEntity<String> getUserPassword(@RequestParam("userName") String userName,
            @RequestParam("password") String password) {
        // Query Parameter 适合多个层级平行的参数。比如，?userName=abc&password=123
        // 对于敏感数据，Query Parameter 也适合，因为密码不应放在URL路径中，所以Path Parameter不适用
        return ResponseEntity.ok(userName + password + "OK");
    }

    @GetMapping("/{userName}/order/{orderId}")
    public ResponseEntity<String> getUserOrder(@PathVariable("userName") String userName,
            @PathVariable("orderId") int orderId) {
        // Path Parameter 适合有固定层级嵌套的参数。例如，/user/abc/order/123，这符合RESTful架构风格
        return ResponseEntity.ok(userName + orderId + "OK");
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        // Query Parameter 另一个常见用途是分页 Pagination
        List<ProductDto> products = productService.getProductsByName(keyword, pageNumber, pageSize);

        if (products == null) {
            throw new ProductException("More Specific Reason Here"); // 交给RestControllerAdvice异常处理器去处理是最佳实践
        } else if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"); // 实际开发中不推荐，这里只做演示
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
