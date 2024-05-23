package spring.code.jake.myproduct;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface MyProductsRepository extends JpaRepository<MyProductEntity, Long> {
    public @NonNull Optional<MyProductEntity> findById(@NonNull Long id);

    // @Query("SELECT  " + "FROM  " + "WHERE  " + "HAVING " + "GROUP BY DESC")
    // public List<MyProductEntity> findProductsByName(Pageable pageable);
}