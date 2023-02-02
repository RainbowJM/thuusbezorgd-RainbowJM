package nl.hu.inno.thuusbezorgd.orders.adapters.in.message;

import nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event.*;
import nl.hu.inno.thuusbezorgd.orders.core.application.OrderCommandService;
import nl.hu.inno.thuusbezorgd.orders.core.application.command.CreateOrderCommand;
import nl.hu.inno.thuusbezorgd.orders.core.application.command.UpdateOrderCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {
    private static final Logger logger = LogManager.getLogger(RabbitMqListener.class.toString());

    private final OrderCommandService orderCommandService;

    public RabbitMqListener(OrderCommandService orderCommandService) {
        this.orderCommandService = orderCommandService;
    }

    @RabbitListener(queues = {"${message.queue.stock-event}"})
    private void listen(StockEvent event){
        switch (event.getEventKey()) {
            case IngredientIncreasedEvent.KEY:
                this.increase((IngredientIncreasedEvent) event);
                break;
            case IngredientDecreasedEvent.KEY:
                this.decrease((IngredientDecreasedEvent) event);
                break;
        }
    }

    private void increase(IngredientIncreasedEvent event){
        logger.info("stock has been increased");
        // for in this future feature this could be handy
    }

    private void decrease(IngredientDecreasedEvent event){
        logger.info("Stock has decreased due to orders that has been placed");
        // for in this future feature this could be handy
    }
    @RabbitListener(queues = {"${message.queue.driver-event}"})
    private void listen(DeliveryEvent event) {
        switch (event.getEventKey()) {
            case DeliveryCreatedEvent.KEY:
                this.create((DeliveryCreatedEvent) event);
                break;
            case DeliveryUpdatedEvent.KEY:
                this.update((DeliveryUpdatedEvent) event);
                break;
        }
    }

    private void create(DeliveryCreatedEvent event){
        this.orderCommandService.create(new CreateOrderCommand(event.getUsername(),
                event.getOrderedDishes(),
                event.getAddress()));
    }

    private void update(DeliveryUpdatedEvent event){
        this.orderCommandService.update(new UpdateOrderCommand(event.getOrderId()));
    }
}
