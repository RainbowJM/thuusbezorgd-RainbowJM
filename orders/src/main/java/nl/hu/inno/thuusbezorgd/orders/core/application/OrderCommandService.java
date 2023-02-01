package nl.hu.inno.thuusbezorgd.orders.core.application;

import nl.hu.inno.thuusbezorgd.orders.adapters.out.event.EventPublisher;
import nl.hu.inno.thuusbezorgd.orders.core.application.command.CreateOrderCommand;
import nl.hu.inno.thuusbezorgd.orders.core.application.command.DeleteOrderCommand;
import nl.hu.inno.thuusbezorgd.orders.core.domain.Dish;
import nl.hu.inno.thuusbezorgd.orders.core.domain.Order;
import nl.hu.inno.thuusbezorgd.orders.core.domain.OrderStatus;
import nl.hu.inno.thuusbezorgd.orders.core.event.OrderCreatedEvent;
import nl.hu.inno.thuusbezorgd.orders.core.event.OrderRemovedEvent;
import nl.hu.inno.thuusbezorgd.orders.core.exception.OrderNotFound;
import nl.hu.inno.thuusbezorgd.orders.core.port.storage.DishRepository;
import nl.hu.inno.thuusbezorgd.orders.core.port.storage.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderCommandService {
    private final OrderRepository orderRepository;

    private final EventPublisher eventPublisher;

    public OrderCommandService(OrderRepository orderRepository, EventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    public Order create(CreateOrderCommand orderCommand){
        Order order = new Order(orderCommand.userName(),
                orderCommand.address(),
                orderCommand.orderedDishes());
        order.setStatus(OrderStatus.InPreparation);

        this.eventPublisher.publish(new OrderCreatedEvent(order.getId(),
                order.getDeliveryId(),
                order.getStatus(),
                order.getAddress()));

        return this.orderRepository.save(order);
    }

    public void delete(DeleteOrderCommand deleteOrderCommand) {
        Optional<Order> orderOpt = this.orderRepository.findById(deleteOrderCommand.orderId());

        if (orderOpt.isEmpty()) {
            throw new OrderNotFound(deleteOrderCommand.orderId());
        }

        Order order = orderOpt.get();

        this.eventPublisher.publish(new OrderRemovedEvent(order.getId()));

        this.orderRepository.delete(order);
    }
}
