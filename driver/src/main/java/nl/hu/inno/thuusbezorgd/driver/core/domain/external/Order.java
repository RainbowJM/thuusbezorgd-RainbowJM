package nl.hu.inno.thuusbezorgd.driver.core.domain.external;

import lombok.*;
import nl.hu.inno.thuusbezorgd.driver.core.domain.Delivery;
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

    private String userId;

    private LocalDateTime orderDate;

    private String deliveryId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    protected Order() {
    }

    public Order(Long id, String userId, LocalDateTime orderDate, String deliveryId, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.deliveryId = deliveryId;
        this.status = OrderStatus.Received;
    }

    //    public Order(User u, Address address) {
//        this.user = u;
////        this.orderedDishes = new ArrayList<>();
//        this.address = address;
//        this.status = OrderStatus.Received;
//    }

    public Long getId() {
        return id;
    }

    public String getUser() {
        return userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

//    public List<Dish> getOrderedDishes() {
//        return this.orderedDishes.stream().map(OrderedDish::getDish).toList();
//    }

//    public List<Long> getOrderedDishIds() {
//        List<Long> ordered = new ArrayList<>();
//
//        for (OrderedDish od : this.orderedDishes) {
//            ordered.add(od.getDishId());
//        }
//
//        return Collections.unmodifiableList(ordered);
//    }

//    public void addDish(Dish dish) {
//        this.orderedDishes.add(new OrderedDish(this, dish));
//    }

//    public Address getAddress() {
////        return address;
////    }
//
//    public Delivery getDelivery() {
//        return delivery;
//    }
//
//    public void setDelivery(Delivery delivery) {
//        this.delivery = delivery;
//    }
//
//    public void setStatus(OrderStatus status) {
//        this.status = status;
//    }

//    public void process(LocalDateTime orderMoment) {
//        this.orderDate = orderMoment;
//        for (Dish d : this.getOrderedDishes()) {
//            d.prepare();
//        }
//    }
}
