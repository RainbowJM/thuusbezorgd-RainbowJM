package nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonTypeName(StockDecreasedEvent.KEY)
public class StockDecreasedEvent extends StockEvent{

    public static final String KEY = "stock.event.decreased";

    @Override
    public String getEventKey() {
        return KEY;
    }
}
