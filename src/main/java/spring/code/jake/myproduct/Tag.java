package spring.code.jake.myproduct;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tag")
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag", nullable = false)
    private String tag;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private MyProductEntity product;
}