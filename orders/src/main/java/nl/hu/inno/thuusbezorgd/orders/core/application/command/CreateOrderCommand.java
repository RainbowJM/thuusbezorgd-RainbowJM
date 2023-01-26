package nl.hu.inno.thuusbezorgd.orders.core.application.command;

import nl.hu.inno.thuusbezorgd.orders.core.domain.Address;
import nl.hu.inno.thuusbezorgd.orders.core.domain.OrderedDish;

import java.util.List;

public record CreateOrderCommand(String userName, List<OrderedDish> orderedDishes, String address) {
}
