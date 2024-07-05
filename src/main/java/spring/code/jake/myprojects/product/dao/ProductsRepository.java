package spring.code.jake.myprojects.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.code.jake.myprojects.product.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword%")
    // 上面的JPQL等价于这段SQL：SELECT p.* FROM product AS p WHERE p.product_name LIKE
    // '%keyword%'
    // JPQL操作的对象是Entity，不是Table
    Page<Product> findByProductNameContaining(@Param("keyword") String keyword, Pageable pageable);
}