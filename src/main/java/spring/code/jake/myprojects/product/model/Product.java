package spring.code.jake.myprojects.product.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Data
@Entity
@DynamicInsert // 为避免null被JPA替换成占位符导致Table Default Value失效
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_sequence", allocationSize = 1) // sequenceName是PostgreSQL内定义的序列名
    @Column(name = "product_id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE) // 防止id被Setter修改
    private Long id; // UUID的索引效率问题?

    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();
    
    
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags); // 是否应该返回不可变集合有争议
        // return Set.copyOf(tags); // Java 10引入的不可变集合，如果tags是空集合，返回的是一个空集合，而不是null
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.setProduct(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.setProduct(null);
    }
}