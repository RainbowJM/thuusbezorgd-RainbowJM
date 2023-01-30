package nl.hu.inno.thuusbezorgd.driver.core.application.command;

public record ChangeDeliveryCommand(Long deliveryId,
                                    Long orderId,
                                    String riderName) {

}