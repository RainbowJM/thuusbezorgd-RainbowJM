package nl.hu.inno.thuusbezorgd.orders.core.domain;

import java.time.LocalDateTime;

public interface TimeProvider {
    LocalDateTime now();
}
