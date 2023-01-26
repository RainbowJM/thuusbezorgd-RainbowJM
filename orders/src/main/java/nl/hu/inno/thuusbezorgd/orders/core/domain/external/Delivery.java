package nl.hu.inno.thuusbezorgd.orders.core.domain.external;

import nl.hu.inno.thuusbezorgd.orders.core.domain.Order;
import nl.hu.inno.thuusbezorgd.orders.core.domain.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedNativeQuery(name = "Delivery.findRandom", resultClass = Delivery.class,
        query = "select * from delivery order by random() limit 1")
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    private boolean completed;

    private String riderName;

    private List<Delivery> deliveries = new ArrayList<>();

    @ManyToOne
    private Order order;

    protected Delivery() {
    }

    public Delivery(Order order, String riderName) {
        this.order = order;
        this.riderName = riderName;
    }

    public Long getId() {
        return id;
    }

    public String getRider() {
        return riderName;
    }

    public Order getOrder() {
        return order;
    }

    public String getRiderName() {
        return riderName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getNrOfDeliveries() {
        return deliveries.size();
    }

    public void markCompleted() {
        this.completed = true;
        this.order.setStatus(OrderStatus.Delivered);
    }
}

