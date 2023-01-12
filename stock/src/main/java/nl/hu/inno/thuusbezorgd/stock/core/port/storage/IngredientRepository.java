package nl.hu.inno.thuusbezorgd.stock.core.port.storage;

import nl.hu.inno.thuusbezorgd.stock.core.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
