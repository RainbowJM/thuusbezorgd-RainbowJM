package nl.hu.inno.thuusbezorgd.driver.core.port.storage;

import nl.hu.inno.thuusbezorgd.driver.core.domain.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Long> {
}
