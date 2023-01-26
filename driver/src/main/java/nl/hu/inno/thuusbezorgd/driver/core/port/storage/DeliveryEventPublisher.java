package nl.hu.inno.thuusbezorgd.driver.core.port.storage;

import nl.hu.inno.thuusbezorgd.driver.core.event.DeliveryEvent;

public interface DeliveryEventPublisher {
    void publish(DeliveryEvent event);
}
