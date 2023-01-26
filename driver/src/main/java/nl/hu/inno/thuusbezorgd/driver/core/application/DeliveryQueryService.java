package nl.hu.inno.thuusbezorgd.driver.core.application;

import nl.hu.inno.thuusbezorgd.driver.core.application.query.DeliveryQuery;
import nl.hu.inno.thuusbezorgd.driver.core.application.query.ListDeliveryQuery;
import nl.hu.inno.thuusbezorgd.driver.core.domain.Delivery;
import nl.hu.inno.thuusbezorgd.driver.core.exception.DeliveryNotFound;
import nl.hu.inno.thuusbezorgd.driver.core.port.storage.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeliveryQueryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryQueryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> get(ListDeliveryQuery listQuery) {
        if (listQuery.riderName() == null){
            this.deliveryRepository.findAll();
        }
        return this.deliveryRepository.findByRiderName(listQuery.riderName());
    }

    public Delivery get(DeliveryQuery query){
        Optional<Delivery> delivery = this.deliveryRepository.findById(query.deliveryId());

        if (delivery.isEmpty()) {
            throw new DeliveryNotFound(query.deliveryId());
        }

        return delivery.get();
    }
}
