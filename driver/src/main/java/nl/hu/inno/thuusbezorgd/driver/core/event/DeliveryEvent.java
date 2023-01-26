package nl.hu.inno.thuusbezorgd.driver.core.event;

import lombok.Getter;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public abstract class DeliveryEvent {
    @Getter
    private final Long id = new Random().nextLong();

    @Getter
    private final Instant timestamp = Instant.now();

    public abstract String getEventKey();
}
