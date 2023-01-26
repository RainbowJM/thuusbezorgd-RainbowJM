package nl.hu.inno.thuusbezorgd.driver.core.port.storage;

import nl.hu.inno.thuusbezorgd.driver.core.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByRiderName(String riderName);
}
