package nl.hu.inno.thuusbezorgd.stock.core.port.storage;

import nl.hu.inno.thuusbezorgd.stock.core.domain.Ingredient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;

@Component
public class StockDataRunner implements CommandLineRunner {
    private final EntityManager entities;

    public StockDataRunner(EntityManager entities) {
        this.entities = entities;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        for (Ingredient i : Arrays.asList(
                new Ingredient("Bun", true),
                new Ingredient("Burger", false),
                new Ingredient("Vega-burger", true),
                new Ingredient("Cheese", false),
                new Ingredient("Lettuce", true),
                new Ingredient("Tomato", true)
        )) {
            i.deliver(10 * 1000);
            entities.persist(i);
        }
    }
}
