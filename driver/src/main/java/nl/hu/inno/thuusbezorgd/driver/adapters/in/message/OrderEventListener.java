package nl.hu.inno.thuusbezorgd.driver.adapters.in.message;

import nl.hu.inno.thuusbezorgd.driver.adapters.in.message.event.OrderCreatedEvent;
import nl.hu.inno.thuusbezorgd.driver.adapters.in.message.event.OrderEvent;
import nl.hu.inno.thuusbezorgd.driver.core.application.DeliveryCommandService;
import nl.hu.inno.thuusbezorgd.driver.core.application.DeliveryQueryService;
import nl.hu.inno.thuusbezorgd.driver.core.domain.external.Order;
import nl.hu.inno.thuusbezorgd.driver.core.port.storage.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderEventListener {

    private final DeliveryQueryService deliveryQueryService;
    private final DeliveryCommandService deliveryCommandService;
    private final OrderRepository orderRepository;

    private static List<Order> orders = new ArrayList<>();

    public static boolean initialized = false;

    public OrderEventListener(DeliveryQueryService deliveryQueryService,
                              DeliveryCommandService deliveryCommandService,
                              OrderRepository orderRepository) {
        this.deliveryQueryService = deliveryQueryService;
        this.deliveryCommandService = deliveryCommandService;
        this.orderRepository = orderRepository;
    }

    private void initializeOrders() {
        if (!initialized) {
            try {
                orders = new ArrayList<>(orderRepository.findAll());
//                System.out.println(orders);
//                System.out.println(orderRepository.findAll());
            }
            catch (ResourceAccessException exception) {
                System.out.println("Failed to connect to Service because it probably hasn't started yet.");
                return;
            }
            initialized = true;
        }
    }

    @EventListener
    public void onReadyEvent(ContextRefreshedEvent event) {
        initializeOrders();
    }

    @RabbitListener(queues = {"${message.queue.order-event}"})
    private void listen(OrderEvent event) {
        switch (event.getEventKey()) {
            case OrderCreatedEvent.KEY:
                this.create((OrderCreatedEvent)event);
        }
    }

    private void create(OrderCreatedEvent event){
        orders.add(new Order(event.getId(),
                event.getUserId(),
                event.getOrderDate(),
                event.getDeliveryId(),
                event.getStatus()));
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static boolean orderExists(Long orderId){
        return orders.stream().anyMatch(order -> order.getId().equals(orderId));
    }
}
