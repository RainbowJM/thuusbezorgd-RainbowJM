package nl.hu.inno.thuusbezorgd.orders.adapters.out.event;

import lombok.AllArgsConstructor;
import nl.hu.inno.thuusbezorgd.orders.core.event.OrderEvent;
import nl.hu.inno.thuusbezorgd.orders.core.port.storage.OrderEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class EventPublisher implements OrderEventPublisher {
    private final String orderEventExchange;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(OrderEvent event) {
        this.rabbitTemplate.convertAndSend(orderEventExchange, event.getEventKey(), event);
    }
}
