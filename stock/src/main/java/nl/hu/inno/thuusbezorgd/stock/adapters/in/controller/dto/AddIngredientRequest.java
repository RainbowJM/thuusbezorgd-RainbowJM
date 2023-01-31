package nl.hu.inno.thuusbezorgd.stock.adapters.in.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AddIngredientRequest {

    @NotBlank
    String ingredientName;

    boolean vegetarian;
}
