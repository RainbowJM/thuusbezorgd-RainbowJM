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

    public List<Ingredient> get(ListIngredientQuery listQuery) {
        if (listQuery.ingredientName() == null) {
            return this.ingredientRepository.findAll();
        }
        return this.ingredientRepository.findIngredientByName(listQuery.ingredientName());
    }

    public Ingredient get(IngredientQuery query) {
        Optional<Ingredient> ingredient = this.ingredientRepository.findById(query.ingredientId());

        if (ingredient.isEmpty()) {
            throw new IngredientNotFound(query.ingredientId());
        }

        return ingredient.get();
    }
}
