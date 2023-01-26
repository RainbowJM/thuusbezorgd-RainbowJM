package nl.hu.inno.thuusbezorgd.driver.adapters.in.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.hu.inno.thuusbezorgd.driver.core.event.DeliveryEvent;

@AllArgsConstructor
@JsonTypeName(DeliveryCreatedEvent.KEY)
public class DeliveryCreatedEvent extends DeliveryEvent {
    public static final String KEY = "delivery.event.created";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final Long orderId;

    @Getter
    private final String riderName;

    @Getter
    private final Long deliveryId;

    @Getter
    private final int nrDelivery;
}
