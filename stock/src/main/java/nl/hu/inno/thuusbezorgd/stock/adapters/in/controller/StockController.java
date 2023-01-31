package nl.hu.inno.thuusbezorgd.stock.adapters.in.controller;

import lombok.extern.log4j.Log4j2;
import nl.hu.inno.thuusbezorgd.stock.adapters.in.controller.dto.AddIngredientRequest;
import nl.hu.inno.thuusbezorgd.stock.core.application.StockCommandService;
import nl.hu.inno.thuusbezorgd.stock.core.application.StockQueryService;
import nl.hu.inno.thuusbezorgd.stock.core.application.command.AddIngredientCommand;
import nl.hu.inno.thuusbezorgd.stock.core.application.command.DeleteIngredientCommand;
import nl.hu.inno.thuusbezorgd.stock.core.application.command.IncreaseIngredientCommand;
import nl.hu.inno.thuusbezorgd.stock.core.application.query.IngredientQuery;
import nl.hu.inno.thuusbezorgd.stock.core.domain.Ingredient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stocks")
@Log4j2
public class StockController {

    private final StockCommandService commandService;

    private final StockQueryService queryService;

    public StockController(StockCommandService stockCommandService, StockQueryService stockQueryService) {
        this.commandService = stockCommandService;
        this.queryService = stockQueryService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Ingredient> getIngredients() {
        return this.queryService.get();
    }

    @GetMapping("/{ingredientName}")
    @ResponseStatus(HttpStatus.OK)
    public Ingredient get(@PathVariable String ingredientName) {
        return this.queryService.get(new IngredientQuery(ingredientName));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient add(@Valid @RequestBody AddIngredientRequest addIngredientRequest) {
        return this.commandService.add(new AddIngredientCommand(addIngredientRequest.getIngredientName(),
                addIngredientRequest.isVegetarian()));
    }

    @PutMapping("/{ingredientName}/amount/{nr}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Ingredient increase(@PathVariable String ingredientName, @PathVariable int nr){
        return this.commandService.increase(new IncreaseIngredientCommand(ingredientName,nr));
    }
    @DeleteMapping("/{ingredientName}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String ingredientName) {
        this.commandService.delete(new DeleteIngredientCommand(ingredientName));
    }

}
