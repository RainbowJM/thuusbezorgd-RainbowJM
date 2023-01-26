package nl.hu.inno.thuusbezorgd.driver.adapters.in.controller;

import nl.hu.inno.thuusbezorgd.driver.adapters.in.controller.dto.CreateDeliveryRequest;
import nl.hu.inno.thuusbezorgd.driver.core.application.DeliveryCommandService;
import nl.hu.inno.thuusbezorgd.driver.core.application.DeliveryQueryService;
import nl.hu.inno.thuusbezorgd.driver.core.application.command.CreateDeliveryCommand;
import nl.hu.inno.thuusbezorgd.driver.core.application.command.DeleteDeliveryCommand;
import nl.hu.inno.thuusbezorgd.driver.core.application.query.DeliveryQuery;
import nl.hu.inno.thuusbezorgd.driver.core.application.query.ListDeliveryQuery;
import nl.hu.inno.thuusbezorgd.driver.core.domain.Delivery;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryQueryService queryService;

    private final DeliveryCommandService commandService;

    public DeliveryController(DeliveryQueryService queryService, DeliveryCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Delivery> getDeliveries(@RequestParam(required = false) String riderName) {
        return this.queryService.get(new ListDeliveryQuery(riderName));
    }

    @GetMapping("/{deliveryId}")
    @ResponseStatus(HttpStatus.OK)
    public Delivery get(@PathVariable String deliveryId) {
        return this.queryService.get(new DeliveryQuery(Long.valueOf(deliveryId)));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery create(@Valid @RequestBody CreateDeliveryRequest createDeliveryRequest) {
        return this.commandService.create(new CreateDeliveryCommand(Long.valueOf(createDeliveryRequest.getOrderId()),
                createDeliveryRequest.getRiderName()));
    }

    @DeleteMapping("/{deliveryId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String deliveryId) {
        this.commandService.delete(new DeleteDeliveryCommand(Long.valueOf(deliveryId)));
    }
}
