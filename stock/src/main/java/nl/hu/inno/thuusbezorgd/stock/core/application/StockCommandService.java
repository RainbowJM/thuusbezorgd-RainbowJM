package nl.hu.inno.thuusbezorgd.stock.core.application;

import nl.hu.inno.thuusbezorgd.stock.adapters.out.event.EventPublisher;
import nl.hu.inno.thuusbezorgd.stock.core.application.command.AddIngredientCommand;
import nl.hu.inno.thuusbezorgd.stock.core.application.command.IncreaseIngredientCommand;
import nl.hu.inno.thuusbezorgd.stock.core.application.command.UseIngredientCommand;
import nl.hu.inno.thuusbezorgd.stock.core.domain.Ingredient;
import nl.hu.inno.thuusbezorgd.stock.core.event.IngredientAddedEvent;
import nl.hu.inno.thuusbezorgd.stock.core.event.IngredientIncreasedEvent;
import nl.hu.inno.thuusbezorgd.stock.core.exception.IngredientNotFound;
import nl.hu.inno.thuusbezorgd.stock.core.port.storage.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StockCommandService {
    private final IngredientRepository ingredientRepository;

    private final EventPublisher eventPublisher;

    public StockCommandService(IngredientRepository ingredientRepository, EventPublisher eventPublisher) {
        this.ingredientRepository = ingredientRepository;
        this.eventPublisher = eventPublisher;
    }

    public Ingredient add(AddIngredientCommand addIngredientCommand) {
        Ingredient ingredient = new Ingredient(addIngredientCommand.name(), addIngredientCommand.vegetarian());

        this.eventPublisher.publish(new IngredientAddedEvent(ingredient.getId(),
                ingredient.getName(),
                ingredient.isVegetarian()));

        return this.ingredientRepository.save(ingredient);
    }

    public void use(UseIngredientCommand useIngredientCommand) {
        Optional<Ingredient> ingredientOpt = this.ingredientRepository.findById(useIngredientCommand.ingredientId());

        if (ingredientOpt.isEmpty()) {
            throw new IngredientNotFound(useIngredientCommand.ingredientId());
        }

        Ingredient ingredient = ingredientOpt.get();
        // TODO: logic to reduce

        this.ingredientRepository.save(ingredient);
    }

    public Ingredient increase(IncreaseIngredientCommand increaseIngredientCommand) {
        Optional<Ingredient> ingredientOpt = this.ingredientRepository.getIngredientByName(increaseIngredientCommand.ingredientName());

        if (ingredientOpt.isEmpty()) {
            throw new IngredientNotFound(increaseIngredientCommand.ingredientName());
        }

        Ingredient ingredient = ingredientOpt.get();

        this.eventPublisher.publish(new IngredientIncreasedEvent(ingredient.getId(),
                ingredient.getName(),
                ingredient.isVegetarian()));

        return this.ingredientRepository.save(ingredient);
    }
}
