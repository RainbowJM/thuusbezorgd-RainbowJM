package nl.hu.inno.thuusbezorgd.driver.core.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DeliveryUpdatedEvent extends  DeliveryEvent {
    public static final String KEY = "delivery.event.updated";
    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final Long deliveryId;
}
