package nl.hu.inno.thuusbezorgd.driver.core.application;

import nl.hu.inno.thuusbezorgd.driver.adapters.in.message.event.DeliveryCreatedEvent;
import nl.hu.inno.thuusbezorgd.driver.adapters.out.event.EventPublisher;
import nl.hu.inno.thuusbezorgd.driver.adapters.in.message.OrderEventListener;
import nl.hu.inno.thuusbezorgd.driver.core.application.command.CreateDeliveryCommand;
import nl.hu.inno.thuusbezorgd.driver.core.application.command.DeleteDeliveryCommand;
import nl.hu.inno.thuusbezorgd.driver.core.domain.Delivery;
import nl.hu.inno.thuusbezorgd.driver.core.exception.DeliveryNotFound;
import nl.hu.inno.thuusbezorgd.driver.core.exception.OrderNotFound;
import nl.hu.inno.thuusbezorgd.driver.core.port.storage.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DeliveryCommandService {
    private final DeliveryRepository deliveryRepository;

    private final EventPublisher eventPublisher;

    public DeliveryCommandService(DeliveryRepository deliveryRepository, EventPublisher eventPublisher) {
        this.deliveryRepository = deliveryRepository;
        this.eventPublisher = eventPublisher;
    }

    public Delivery create(CreateDeliveryCommand createDeliveryCommand) {

//        if (OrderEventListener.orderExists(createDeliveryCommand.orderId())){
            Delivery delivery = new Delivery(createDeliveryCommand.orderId(),
                    createDeliveryCommand.riderName());

            this.eventPublisher.publish(new DeliveryCreatedEvent(delivery.getOrderId(),
                    delivery.getRiderName(),
                    delivery.getId(),
                    delivery.getNrOfDeliveries()));
            return this.deliveryRepository.save(delivery);
//        }
//        else throw new OrderNotFound(createDeliveryCommand.orderId());
    }

    public void delete(DeleteDeliveryCommand deleteDeliveryCommand){
        Optional<Delivery> delivery = this.deliveryRepository.findById(deleteDeliveryCommand.deliveryId());

        if (delivery.isEmpty()){
            throw new DeliveryNotFound(deleteDeliveryCommand.deliveryId());
        }

        this.deliveryRepository.delete(delivery.get());
    }
}
