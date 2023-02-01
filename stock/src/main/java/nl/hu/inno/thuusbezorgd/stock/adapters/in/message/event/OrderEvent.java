package nl.hu.inno.thuusbezorgd.stock.adapters.in.message.event;

import lombok.Getter;

import java.time.Instant;
import java.util.Random;


public abstract class OrderEvent {
    @Getter
    private final Long id = new Random().nextLong();
    @Getter
    private final Instant timestamp = Instant.now();

    public abstract String getEventKey();
}