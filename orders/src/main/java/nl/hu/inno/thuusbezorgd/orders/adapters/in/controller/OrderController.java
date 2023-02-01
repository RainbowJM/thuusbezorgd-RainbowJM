package nl.hu.inno.thuusbezorgd.orders.adapters.in.controller;

import nl.hu.inno.thuusbezorgd.orders.adapters.in.controller.dto.CreateOrderRequest;
import nl.hu.inno.thuusbezorgd.orders.core.application.OrderCommandService;
import nl.hu.inno.thuusbezorgd.orders.core.application.OrderQueryService;
import nl.hu.inno.thuusbezorgd.orders.core.application.command.CreateOrderCommand;
import nl.hu.inno.thuusbezorgd.orders.core.application.command.DeleteOrderCommand;
import nl.hu.inno.thuusbezorgd.orders.core.application.query.OrderQuery;
import nl.hu.inno.thuusbezorgd.orders.core.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderQueryService queryService;

    private final OrderCommandService commandService;

    public OrderController(OrderQueryService queryService, OrderCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders() {
        return this.queryService.get();
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Order get(@PathVariable int orderId) {
        return this.queryService.get(new OrderQuery(Long.valueOf(orderId)));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        return this.commandService.create(new CreateOrderCommand(createOrderRequest.getUserName(),
                createOrderRequest.getOrderedDishes(),
                createOrderRequest.getAddress()));
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int orderId) {
        this.commandService.delete(new DeleteOrderCommand(Long.valueOf(orderId)));
    }
}
