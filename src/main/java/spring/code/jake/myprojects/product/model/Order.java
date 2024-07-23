package spring.code.jake.myprojects.product.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Builder
public class Order {

    @Id
    private UUID id;

    @ToString.Exclude // 防止循环引用
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false) // 双向关联 (Bidirectional Association) 
    private Customer customer;

    public void setCustomer(Customer customer) {
        if (this.customer != null) {
            this.customer.getOrders().remove(this);
        }
        this.customer = customer;
        if (customer != null) {
            customer.getOrders().add(this);
        }
    }
}
