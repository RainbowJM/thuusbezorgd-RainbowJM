package nl.hu.inno.thuusbezorgd.driver.adapters.out.event;

import lombok.AllArgsConstructor;
import nl.hu.inno.thuusbezorgd.driver.core.event.DeliveryEvent;
import nl.hu.inno.thuusbezorgd.driver.core.port.storage.DeliveryEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class EventPublisher implements DeliveryEventPublisher {
    private final String deliveryEventExchange;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(DeliveryEvent event) {
        this.rabbitTemplate.convertAndSend(deliveryEventExchange, event.getEventKey(), event);
    }
}
