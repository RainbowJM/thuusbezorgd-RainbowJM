package nl.hu.inno.thuusbezorgd.orders.core.port.storage;

import nl.hu.inno.thuusbezorgd.orders.core.event.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent event);
}
