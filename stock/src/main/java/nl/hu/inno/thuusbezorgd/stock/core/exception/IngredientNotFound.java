package nl.hu.inno.thuusbezorgd.stock.core.exception;

public class IngredientNotFound extends RuntimeException {

    public IngredientNotFound(Long ingredientId) {
        super(String.format("Ingredient %s is invalid", ingredientId));
    }
    public IngredientNotFound(String ingredientName) {
        super(String.format("Ingredient %s is invalid", ingredientName));
    }
}
