package nl.hu.inno.thuusbezorgd.orders.core.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.hu.inno.thuusbezorgd.orders.core.domain.OrderStatus;

@AllArgsConstructor
public class OrderCreatedEvent extends OrderEvent {
    public static final String KEY = "order.event.created";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final Long orderId;

    @Getter
    private final Long driverId;

    @Getter
    private final OrderStatus status;

    @Getter
    private final String deliveryAddress;
}
