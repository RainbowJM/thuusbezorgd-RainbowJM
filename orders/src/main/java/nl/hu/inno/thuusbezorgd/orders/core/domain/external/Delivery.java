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

    private Long orderId;

    protected Delivery() {
    }

    public Delivery(Long order, String riderName) {
        this.orderId = order;
        this.riderName = riderName;
    }

    public Long getId() {
        return id;
    }

    public String getRider() {
        return riderName;
    }

    public Long getOrder() {
        return orderId;
    }

    public String getRiderName() {
        return riderName;
    }

    public boolean isCompleted() {
        return completed;
    }


}

