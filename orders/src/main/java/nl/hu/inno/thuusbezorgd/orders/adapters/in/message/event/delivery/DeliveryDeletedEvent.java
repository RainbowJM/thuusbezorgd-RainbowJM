package nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event.delivery;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonTypeName(DeliveryDeletedEvent.KEY)
public class DeliveryDeletedEvent extends DeliveryEvent {
    public static final String KEY = "driver.event.deleted";

    @Override
    public String getEventKey() {
        return KEY;
    }
}
