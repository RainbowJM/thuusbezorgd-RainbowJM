package nl.hu.inno.thuusbezorgd.orders.adapters.out.event;

import lombok.AllArgsConstructor;
import nl.hu.inno.thuusbezorgd.orders.core.event.OrderEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class EventPublisher {
    private final String orderEventExchange;
    private final RabbitTemplate rabbitTemplate;

    public void publish(OrderEvent event) {
        this.rabbitTemplate.convertAndSend(orderEventExchange, event.getEventKey(), event);
    }
}
