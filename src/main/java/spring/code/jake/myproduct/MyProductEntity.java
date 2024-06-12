package spring.code.jake.myproduct;

import java.io.Serializable;
import java.util.*;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@DynamicInsert // 为避免null被JPA替换成占位符导致Table Default Value失效
@Table(name = "products")
@NoArgsConstructor
public class MyProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_sequence", allocationSize = 1) // sequenceName是PostgreSQL内定义的序列名
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @OneToMany(mappedBy = "myProductEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tag> tags = new HashSet<>();
}