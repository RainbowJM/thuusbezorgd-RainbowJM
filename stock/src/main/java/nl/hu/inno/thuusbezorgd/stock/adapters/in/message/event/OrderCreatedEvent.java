package nl.hu.inno.thuusbezorgd.stock.adapters.in.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@JsonTypeName(OrderCreatedEvent.KEY)
public class OrderCreatedEvent extends OrderEvent {
    public static final String KEY = "order.event.created";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String userId;

    @Getter
    private final LocalDateTime orderDate;

    @Getter
    private final String deliveryId;

    @Getter
    private final Long ingredientId;
}
