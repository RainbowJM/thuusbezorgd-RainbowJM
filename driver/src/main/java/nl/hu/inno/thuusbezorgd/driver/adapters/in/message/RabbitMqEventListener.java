package nl.hu.inno.thuusbezorgd.driver.adapters.in.message;

import nl.hu.inno.thuusbezorgd.driver.adapters.in.message.event.OrderCreatedEvent;
import nl.hu.inno.thuusbezorgd.driver.adapters.in.message.event.OrderRemovedEvent;
import nl.hu.inno.thuusbezorgd.driver.adapters.in.message.event.OrderEvent;
import nl.hu.inno.thuusbezorgd.driver.core.application.DeliveryCommandService;
import nl.hu.inno.thuusbezorgd.driver.core.application.command.CreateDeliveryCommand;
import nl.hu.inno.thuusbezorgd.driver.core.application.command.DeleteDeliveryCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {

    private final DeliveryCommandService deliveryCommandService;

    public RabbitMqEventListener(DeliveryCommandService deliveryCommandService) {
        this.deliveryCommandService = deliveryCommandService;
    }

    @RabbitListener(queues = {"${message.queue.order-event}"})
    private void listen(OrderEvent event) {
        switch (event.getEventKey()) {
            case OrderCreatedEvent.KEY:
                this.create((OrderCreatedEvent) event);
                break;
            case OrderRemovedEvent.KEY:
                this.delete((OrderRemovedEvent) event);
                break;
        }
    }

    private void create(OrderCreatedEvent event) {
        this.deliveryCommandService.create(new CreateDeliveryCommand(event.getId()));
    }

    private void delete(OrderRemovedEvent event) {
        this.deliveryCommandService.delete(new DeleteDeliveryCommand(event.getDeliveryId()));
    }
}
