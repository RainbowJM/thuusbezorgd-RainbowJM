package nl.hu.inno.thuusbezorgd.orders.core.port.storage;

import nl.hu.inno.thuusbezorgd.orders.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
