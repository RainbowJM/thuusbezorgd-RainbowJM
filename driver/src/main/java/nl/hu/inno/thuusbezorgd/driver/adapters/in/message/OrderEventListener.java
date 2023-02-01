package nl.hu.inno.thuusbezorgd.driver.adapters.in.message;

import nl.hu.inno.thuusbezorgd.driver.adapters.in.message.event.OrderCreatedEvent;
import nl.hu.inno.thuusbezorgd.driver.adapters.in.message.event.OrderEvent;
import nl.hu.inno.thuusbezorgd.driver.core.application.DeliveryCommandService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {

    private final DeliveryCommandService deliveryCommandService;

    public OrderEventListener(DeliveryCommandService deliveryCommandService) {
        this.deliveryCommandService = deliveryCommandService;
    }

    @RabbitListener(queues = {"${message.queue.order-event}"})
    private void listen(OrderEvent event) {
        switch (event.getEventKey()) {
            case OrderCreatedEvent.KEY:
                System.out.println("hi hi");
//                this.create((OrderCreatedEvent) event);
        }
    }
}
