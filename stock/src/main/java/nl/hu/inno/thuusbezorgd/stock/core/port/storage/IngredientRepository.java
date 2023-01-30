package nl.hu.inno.thuusbezorgd.stock.core.port.storage;

import nl.hu.inno.thuusbezorgd.stock.core.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findIngredientByName(String ingredientName);
}
