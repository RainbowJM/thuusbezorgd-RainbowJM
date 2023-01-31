package nl.hu.inno.thuusbezorgd.stock.core.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.Key;

@AllArgsConstructor
public class IngredientIncreasedEvent extends StockEvent{

    public static final String KEY = "stock.event.increased";
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
