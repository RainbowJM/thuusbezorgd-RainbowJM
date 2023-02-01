package nl.hu.inno.thuusbezorgd.stock.adapters.in.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(OrderDeletedEvent.KEY)
public class OrderDeletedEvent extends OrderEvent {
    public static final String KEY = "order.event.deleted";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final Long deliveryId;

    @Getter
    private final String ingredientName;

    @Getter
    private final int ingredientAmount;
}
