package spring.code.jake.myprojects.product.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false) // 双向关联 (Bidirectional Association) 
    private Customer customer;
}
