package nl.hu.inno.thuusbezorgd.driver.core.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DeliveryCreatedEvent extends DeliveryEvent {
    public static final String KEY = "driver.event.created";

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
