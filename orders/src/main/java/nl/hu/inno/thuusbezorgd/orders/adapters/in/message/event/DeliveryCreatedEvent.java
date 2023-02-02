package nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.hu.inno.thuusbezorgd.orders.core.domain.Dish;

import java.util.List;

@AllArgsConstructor
@JsonTypeName(DeliveryCreatedEvent.KEY)
public class DeliveryCreatedEvent extends DeliveryEvent {

    public static final String KEY = "driver.event.created";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final Long orderId;

    @Getter
    private final String username;

    @Getter
    private final List<Dish> orderedDishes;

    @Getter
    private final String address;
}
