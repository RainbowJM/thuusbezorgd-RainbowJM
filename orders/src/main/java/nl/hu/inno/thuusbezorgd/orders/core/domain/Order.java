package nl.hu.inno.thuusbezorgd.orders.core.domain;

import nl.hu.inno.thuusbezorgd.orders.core.domain.external.Delivery;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "orders") //Order is een keyword in sql, so this works around some wonky sql-generator implementations
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private Long deliveryId;

    private String address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    @ManyToMany
    @Cascade(CascadeType.PERSIST)
    private List<Dish> orderedDishes;

    protected Order() {
    }

    public Order(String username, String address, List<Dish> orderedDishes) {
        this.userName = username;
        this.orderedDishes = orderedDishes;
        this.address = address;
        this.status = getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getUser() {
        return userName;
    }

    public List<Dish> getOrderedDishes() {
        return orderedDishes;
    }

    public void addDish(Dish dish) {
        this.orderedDishes.add(dish);
    }

    public String getAddress() {
        return address;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
