package nl.hu.inno.thuusbezorgd.orders.core.port.storage;

import nl.hu.inno.thuusbezorgd.orders.core.domain.Order;
import nl.hu.inno.thuusbezorgd.orders.core.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser(User currentUser);
}
