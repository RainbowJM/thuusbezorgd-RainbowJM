package nl.hu.inno.thuusbezorgd.stock.core.event;

import lombok.Getter;

import java.time.Instant;
import java.util.Random;

public abstract class StockEvent {

    @Getter
    private final long id = new Random().nextLong();

    @Getter
    private final Instant timestamp = Instant.now();

    public abstract String getEventKey();
}
