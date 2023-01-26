package nl.hu.inno.thuusbezorgd.orders.adapters.in.controller.dto;

import lombok.Getter;
import nl.hu.inno.thuusbezorgd.orders.core.domain.Address;
import nl.hu.inno.thuusbezorgd.orders.core.domain.OrderedDish;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class CreateOrderRequest {

    @NotBlank(message = "userName can't be empty")
    String userName;

    @NotBlank(message = "Address can't be empty")
    String address;

    List<OrderedDish> orderedDishes;
}
