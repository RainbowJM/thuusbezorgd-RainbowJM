package nl.hu.inno.thuusbezorgd.stock.core.port.storage;

import nl.hu.inno.thuusbezorgd.stock.core.event.StockEvent;

public interface StockEventPublisher {
    void publish(StockEvent event);
}
