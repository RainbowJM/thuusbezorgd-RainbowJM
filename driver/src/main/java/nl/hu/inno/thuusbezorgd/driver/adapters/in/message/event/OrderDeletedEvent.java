package nl.hu.inno.thuusbezorgd.driver.adapters.in.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonTypeName(OrderDeletedEvent.KEY)
public class OrderDeletedEvent extends OrderEvent {
    public static final String KEY = "order.event.deleted";

    @Override
    public String getEventKey() {
        return KEY;
    }


}