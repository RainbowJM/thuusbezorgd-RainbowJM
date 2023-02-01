package nl.hu.inno.thuusbezorgd.orders.adapters.in.controller.dto;

import lombok.Getter;
import nl.hu.inno.thuusbezorgd.orders.core.domain.Dish;
import nl.hu.inno.thuusbezorgd.orders.core.domain.OrderStatus;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class CreateOrderRequest {

    @NotBlank(message = "userName can't be empty")
    String userName;

    @NotBlank(message = "Address can't be empty")
    String address;

    List<Dish> orderedDishes;

    OrderStatus status;

    public CreateOrderRequest(String userName, String address, List<Dish> orderedDishes) {
        this.userName = userName;
        this.address = address;
        this.orderedDishes = orderedDishes;
        this.status = OrderStatus.Received;
    }
}
