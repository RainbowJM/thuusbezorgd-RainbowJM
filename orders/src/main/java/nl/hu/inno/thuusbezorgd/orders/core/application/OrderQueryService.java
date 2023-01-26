package nl.hu.inno.thuusbezorgd.orders.core.application;

import nl.hu.inno.thuusbezorgd.orders.core.application.query.OrderQuery;
import nl.hu.inno.thuusbezorgd.orders.core.domain.Order;
import nl.hu.inno.thuusbezorgd.orders.core.exception.OrderNotFound;
import nl.hu.inno.thuusbezorgd.orders.core.port.storage.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OrderQueryService {
    private final OrderRepository orderRepository;

    public OrderQueryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order get(OrderQuery query){
        Optional<Order> order = this.orderRepository.findById(query.orderId());

        if (order.isEmpty()) {
            throw new OrderNotFound(query.orderId());
        }

        return order.get();
    }
}
