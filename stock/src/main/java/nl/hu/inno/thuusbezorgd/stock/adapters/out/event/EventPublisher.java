package nl.hu.inno.thuusbezorgd.stock.adapters.out.event;

import lombok.AllArgsConstructor;
import nl.hu.inno.thuusbezorgd.stock.core.event.StockEvent;
import nl.hu.inno.thuusbezorgd.stock.core.port.storage.StockEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class EventPublisher implements StockEventPublisher {

    private final String stockEventExchange;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(StockEvent event){
        this.rabbitTemplate.convertAndSend(stockEventExchange, event.getEventKey(), event);
    }
}
