package nl.hu.inno.thuusbezorgd.orders.core.application.command;

import nl.hu.inno.thuusbezorgd.orders.core.domain.Dish;

import java.util.List;

public record CreateOrderCommand(String userName, List<Dish> orderedDishes, String address) {
}
