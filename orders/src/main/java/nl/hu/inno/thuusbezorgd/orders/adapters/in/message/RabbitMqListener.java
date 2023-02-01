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
    private static Logger logger = LogManager.getLogger(RabbitMqListener.class.toString());

    private final OrderCommandService orderCommandService;

    public RabbitMqListener(OrderCommandService orderCommandService) {
        this.orderCommandService = orderCommandService;
    }

    @RabbitListener(queues = {"${message.queue.stock-event}"})
    private void listen(StockEvent event){
        switch (event.getEventKey()) {
            case StockIncreasedEvent.KEY:
                this.increase((StockIncreasedEvent) event);
                break;
            case StockDecreasedEvent.KEY:
                this.decrease((StockDecreasedEvent) event);
                break;
        }
    }

    private void increase(StockIncreasedEvent event){
        logger.info("stock has been increased");
        // for in this future feature this could be handy
    }

    private void decrease(StockDecreasedEvent event){
        logger.info("Stock has decreased due to orders that has been placed");
        // for in this future feature this could be handy
    }
    @RabbitListener(queues = {"${message.queue.driver-event}"})
    private void listen(DriverEvent event) {
        switch (event.getEventKey()) {
            case DriverCreatedEvent.KEY:
                this.create((DriverCreatedEvent) event);
                break;
            case DriverUpdatedEvent.KEY:
                this.update((DriverUpdatedEvent) event);
                break;
        }
    }

    private void create(DriverCreatedEvent event){
        this.orderCommandService.create(new CreateOrderCommand(event.getUsername(),
                event.getOrderedDishes(),
                event.getAddress()));
    }

    private void update(DriverUpdatedEvent event){
        this.orderCommandService.update(new UpdateOrderCommand(event.getOrderId()));
    }
}
