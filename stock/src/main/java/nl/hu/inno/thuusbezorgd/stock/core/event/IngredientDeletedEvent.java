package nl.hu.inno.thuusbezorgd.stock.core.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class IngredientDeletedEvent extends StockEvent {

    public static final String KEY = "stock.event.deleted";
    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final Long ingredientId;
}
