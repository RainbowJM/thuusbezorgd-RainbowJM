package nl.hu.inno.thuusbezorgd.orders.adapters.in.message;

import nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event.delivery.DeliveryCreatedEvent;
import nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event.delivery.DeliveryEvent;
import nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event.delivery.DeliveryUpdatedEvent;
import nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event.ingredient.*;
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
            case IngredientDeletedEvent.KEY:
                this.delete((IngredientDeletedEvent) event);
                break;
            case IngredientAddedEvent.KEY:
                this.add((IngredientAddedEvent) event);
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
    private void add(IngredientAddedEvent event) {
        logger.info("New ingredient added, menu can be updated now");
    }

    private void delete(IngredientDeletedEvent event) {
        logger.info("Ingredient was removed, can not order it anymore");
    }
    @RabbitListener(queues = {"${message.queue.driver-event}"})
    private void listen(DeliveryEvent event) {
        logger.info(event.getEventKey());
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
        logger.info("Order has a delivery order");
    }

    private void update(DeliveryUpdatedEvent event){
        logger.info("Order has been delivered");
        this.orderCommandService.update(new UpdateOrderCommand(event.getId()));
    }
}
