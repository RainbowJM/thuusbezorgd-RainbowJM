package nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event.ingredient;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonTypeName(IngredientDeletedEvent.KEY)
public class IngredientDeletedEvent extends StockEvent {
    public static final String KEY = "stock.event.deleted";
    @Override
    public String getEventKey() {
        return KEY;
    }
}
