package nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(DeliveryUpdatedEvent.KEY)
public class DeliveryUpdatedEvent extends DeliveryEvent {
    public static final String KEY = "driver.event.updated";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final Long orderId;
}
