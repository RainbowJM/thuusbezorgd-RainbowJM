package nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonTypeName(StockIncreasedEvent.KEY)
public class StockIncreasedEvent extends StockEvent{
    public static final String KEY = "stock.event.increased";

    @Override
    public String getEventKey() {
        return KEY;
    }
}
