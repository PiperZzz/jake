package spring.code.jake.myprojects.product.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.io.Serializable;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "tags")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag", nullable = false)
    private String tag;

    @ManyToOne
    @JoinColumn(name = "product_id_seq", nullable = false)
    private Product product;
}