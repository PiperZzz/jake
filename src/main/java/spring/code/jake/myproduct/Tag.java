package spring.code.jake.myproduct;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "tags")
@NoArgsConstructor
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag", nullable = false)
    private String tag;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private MyProductEntity myProductEntity;
}