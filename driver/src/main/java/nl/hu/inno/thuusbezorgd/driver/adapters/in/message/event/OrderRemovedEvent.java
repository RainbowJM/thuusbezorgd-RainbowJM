package nl.hu.inno.thuusbezorgd.driver.adapters.in.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(OrderRemovedEvent.KEY)
public class OrderRemovedEvent extends OrderEvent {
    public static final String KEY = "order.event.deleted";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final Long deliveryId;
}
