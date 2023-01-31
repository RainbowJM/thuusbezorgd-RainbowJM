package nl.hu.inno.thuusbezorgd.stock.core.application;

import nl.hu.inno.thuusbezorgd.stock.core.application.query.IngredientQuery;
import nl.hu.inno.thuusbezorgd.stock.core.application.query.ListIngredientQuery;
import nl.hu.inno.thuusbezorgd.stock.core.domain.Ingredient;
import nl.hu.inno.thuusbezorgd.stock.core.exception.IngredientNotFound;
import nl.hu.inno.thuusbezorgd.stock.core.port.storage.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StockQueryService {
    private final IngredientRepository ingredientRepository;

    public StockQueryService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> get() {
            return this.ingredientRepository.findAll();
    }

    public Ingredient get(IngredientQuery query) {
        Optional<Ingredient> ingredient = this.ingredientRepository.getIngredientByName(query.ingredientName());

        if (ingredient.isEmpty()) {
            throw new IngredientNotFound(query.ingredientName());
        }

        return ingredient.get();
    }
}
