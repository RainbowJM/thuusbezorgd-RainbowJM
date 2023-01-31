package nl.hu.inno.thuusbezorgd.stock.core.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class IngredientAddedEvent extends StockEvent {
    public static final String KEY = "stock.event.added";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final Long ingredientId;

    @Getter
    private final String name;

    @Getter
    private final boolean vegetarian;
}
