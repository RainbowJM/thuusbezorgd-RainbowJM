package nl.hu.inno.thuusbezorgd.orders.core.port.storage;

import nl.hu.inno.thuusbezorgd.orders.core.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {

}
