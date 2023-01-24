package nl.hu.inno.thuusbezorgd.orders.core.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class OrderCreatedEvent extends OrderEvent {
    public static final String KEY = "order.event.created";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String orderId;

    @Getter
    private final String driverId;

    @Getter
    private final String status;

    @Getter
    private final String deliveryAddress;
}
