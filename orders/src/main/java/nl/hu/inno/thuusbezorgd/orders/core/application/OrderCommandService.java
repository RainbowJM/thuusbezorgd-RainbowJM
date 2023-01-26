package nl.hu.inno.thuusbezorgd.orders.core.application;

import nl.hu.inno.thuusbezorgd.orders.adapters.out.event.EventPublisher;
import nl.hu.inno.thuusbezorgd.orders.core.application.command.CreateOrderCommand;
import nl.hu.inno.thuusbezorgd.orders.core.domain.Dish;
import nl.hu.inno.thuusbezorgd.orders.core.domain.Order;
import nl.hu.inno.thuusbezorgd.orders.core.domain.OrderedDish;
import nl.hu.inno.thuusbezorgd.orders.core.port.storage.DishRepository;
import nl.hu.inno.thuusbezorgd.orders.core.port.storage.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderCommandService {
    private final OrderRepository orderRepository;

    private final DishRepository dishRepository;

    private final EventPublisher eventPublisher;

    public OrderCommandService(OrderRepository orderRepository, DishRepository dishRepository, EventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
        this.eventPublisher = eventPublisher;
    }

    public Order create(CreateOrderCommand orderCommand){
        Order order;
        List<OrderedDish> orderedDishes = orderCommand.orderedDishes();

        order = new Order(orderCommand.userName(), orderCommand.address());

        return this.orderRepository.save(order);
    }
}
