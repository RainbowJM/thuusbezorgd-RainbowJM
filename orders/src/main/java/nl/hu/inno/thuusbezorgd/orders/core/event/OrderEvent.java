package nl.hu.inno.thuusbezorgd.orders.core.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

public abstract class OrderEvent {
    @Getter
    private final UUID id = UUID.randomUUID();
    @Getter
    private final Instant timestamp = Instant.now();

    public abstract String getEventKey();
}
