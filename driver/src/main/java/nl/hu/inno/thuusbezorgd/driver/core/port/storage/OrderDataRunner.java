package nl.hu.inno.thuusbezorgd.driver.core.port.storage;

import nl.hu.inno.thuusbezorgd.driver.core.domain.Delivery;
import nl.hu.inno.thuusbezorgd.driver.core.domain.external.Order;
import nl.hu.inno.thuusbezorgd.driver.core.domain.external.OrderStatus;
import nl.hu.inno.thuusbezorgd.driver.core.domain.external.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class OrderDataRunner implements CommandLineRunner {
    private final EntityManager entities;

    public OrderDataRunner(EntityManager entities) {
        this.entities = entities;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        for (Order i : Arrays.asList(
                new Order(null,
                        "09",
                        LocalDateTime.now(),
                        "8",
                                OrderStatus.ReadyForDelivery),
                new Order(null,
                        "08",
                        LocalDateTime.now(),
                        "9",
                        OrderStatus.ReadyForDelivery)
        )) {
            entities.persist(i);
        }
    }
}
