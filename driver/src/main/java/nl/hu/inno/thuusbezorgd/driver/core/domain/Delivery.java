package nl.hu.inno.thuusbezorgd.driver.core.domain;

import nl.hu.inno.thuusbezorgd.driver.core.domain.external.Order;
import nl.hu.inno.thuusbezorgd.driver.core.domain.external.OrderStatus;

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

    @ElementCollection
    private List<Delivery> deliveries = new ArrayList<>();

    private Long orderId;

    protected Delivery() {
    }

    public Delivery(Long orderId, String riderName) {
        this.orderId = orderId;
        this.riderName = riderName;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
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
    }
}

