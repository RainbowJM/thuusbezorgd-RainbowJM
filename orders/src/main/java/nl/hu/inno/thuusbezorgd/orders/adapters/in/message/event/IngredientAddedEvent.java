package nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonTypeName(IngredientAddedEvent.KEY)
public class IngredientAddedEvent extends StockEvent {

    public static final String KEY = "stock.event.added";

    @Override
    public String getEventKey() {
        return KEY;
    }
}
