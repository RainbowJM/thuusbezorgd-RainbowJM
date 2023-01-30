package nl.hu.inno.thuusbezorgd.driver.core.application.command;

import java.util.List;

public record ChangeDeliveryCommand(Long deliveryId,
                                    Long orderId,
                                    String riderName) {

}