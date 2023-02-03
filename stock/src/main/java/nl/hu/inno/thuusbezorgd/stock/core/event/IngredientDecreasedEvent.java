package nl.hu.inno.thuusbezorgd.stock.core.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class IngredientDecreasedEvent extends  StockEvent{
    public static final String KEY = "stock.event.decreased";

    @Override
    public String getEventKey() {
        return KEY;
    }
}
