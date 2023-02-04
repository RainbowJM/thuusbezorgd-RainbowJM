package nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event.ingredient;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonTypeName(IngredientDecreasedEvent.KEY)
public class IngredientDecreasedEvent extends StockEvent {

    public static final String KEY = "stock.event.decreased";

    @Override
    public String getEventKey() {
        return KEY;
    }
}
