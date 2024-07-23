package spring.code.jake.myprojects.product.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true) // 双向关联 (Bidirectional Association)
    // 如果是内向关联@JoinColumn在这里，否则在“Many”实体（Order）内维护
    private Set<Order> orders = new HashSet<>(); // 尽管@Builder会初始化，这里还是保险起见

    public void addOrder(Order order) {
        if (order == null) 
            throw new NullPointerException("Customer is null");
        if (orders == null) 
            orders = new HashSet<>();
        if (order.getCustomer() != null) 
            throw new IllegalStateException("The order is already assigned to a customer.");
        if (orders.contains(order)) 
            throw new IllegalStateException("The order is already in the customer's collection.");
        orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(Order order) {
        if (order != null && orders != null) {
            orders.remove(order);
            order.setCustomer(null);
        }
    }
}
