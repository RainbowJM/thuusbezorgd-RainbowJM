package nl.hu.inno.thuusbezorgd.stock.core.application;

import nl.hu.inno.thuusbezorgd.stock.core.application.query.IngredientQuery;
import nl.hu.inno.thuusbezorgd.stock.core.domain.Ingredient;
import nl.hu.inno.thuusbezorgd.stock.core.exception.IngredientNotFound;
import nl.hu.inno.thuusbezorgd.stock.core.port.storage.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StockQueryService {
    private final IngredientRepository ingredientRepository;

    public StockQueryService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient get(IngredientQuery query) {
        Optional<Ingredient> ingredient = this.ingredientRepository.findById(query.ingredientId());

        if (ingredient.isEmpty()) {
            throw new IngredientNotFound(query.ingredientId());
        }

        return ingredient.get();
    }
}
