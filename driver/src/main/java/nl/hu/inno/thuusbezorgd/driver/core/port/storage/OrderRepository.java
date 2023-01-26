package nl.hu.inno.thuusbezorgd.driver.core.port.storage;

import nl.hu.inno.thuusbezorgd.driver.core.domain.external.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {


}
