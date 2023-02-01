package nl.hu.inno.thuusbezorgd.orders.core.domain;

import nl.hu.inno.thuusbezorgd.orders.core.domain.external.Ingredient;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Dish {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Ingredient> ingredients;

    protected Dish() {
        //For Hibernate
    }

    public Dish(String name, Ingredient... ingredients) {
        if (ingredients.length == 0) {
            throw new IllegalArgumentException("Cannot have 0 ingredients");
        }

        this.name = name;
        this.ingredients = Arrays.asList(ingredients);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public boolean isVegetarian() {
        return this.ingredients.stream().allMatch(Ingredient::isVegetarian);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) && Objects.equals(name, dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
