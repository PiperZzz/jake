package spring.code.jake.myprojects.product.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    private UUID id;

    @OneToMany(mappedBy = "customer") // 双向关联 (Bidirectional Association)
    // 如果是内向关联@JoinColumn在这里，否则在“Many”实体（Order）内维护
    private Set<Order> orders; // 由于@Builder，这里不需要初始化

    public void addOrder(Order order) {
        if (order == null) 
            throw new NullPointerException("Customer is null");
        if (order.getCustomer() != null) 
            throw new IllegalStateException("Customer already contains this order");        
        orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setCustomer(null);
    }
}
