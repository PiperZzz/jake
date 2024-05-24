package spring.code.jake.myproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface MyProductsRepository extends JpaRepository<MyProductEntity, Long> {

    @Query("SELECT p FROM MyProductEntity p WHERE p.productName LIKE %:keyword%")
    // 上面的JPQL等价于这段SQL：SELECT p.* FROM products AS p WHERE p.product_name LIKE '%keyword%'
    // JPQL操作的对象是Entity，不是Table
    Page<MyProductEntity> findByProductNameContaining(@Param("keyword") String keyword, Pageable pageable);
}