package nl.hu.inno.thuusbezorgd.stock.adapters.in.message;

import nl.hu.inno.thuusbezorgd.stock.adapters.in.message.event.OrderCreatedEvent;
import nl.hu.inno.thuusbezorgd.stock.adapters.in.message.event.OrderDeletedEvent;
import nl.hu.inno.thuusbezorgd.stock.adapters.in.message.event.OrderEvent;
import nl.hu.inno.thuusbezorgd.stock.core.application.StockCommandService;
import nl.hu.inno.thuusbezorgd.stock.core.application.command.IncreaseIngredientCommand;
import nl.hu.inno.thuusbezorgd.stock.core.application.command.UseIngredientCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {
    private final StockCommandService stockCommandService;

    public RabbitMqListener(StockCommandService stockCommandService) {
        this.stockCommandService = stockCommandService;
    }


    @RabbitListener(queues = {"${message.queue.order-event}"})
    private void listen(OrderEvent event) {
        switch (event.getEventKey()) {
            case OrderCreatedEvent.KEY:
                this.create((OrderCreatedEvent) event);
                break;
            case OrderDeletedEvent.KEY:
                this.delete((OrderDeletedEvent) event);
                break;
        }
    }

    private void create(OrderCreatedEvent event) {
        this.stockCommandService.decrease(new UseIngredientCommand(event.getIngredientId()));
    }

    private void delete(OrderDeletedEvent event) {
        this.stockCommandService.increase(new IncreaseIngredientCommand(event.getIngredientName(),
                event.getIngredientAmount()));
    }
}


