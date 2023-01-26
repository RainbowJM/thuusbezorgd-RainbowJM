package nl.hu.inno.thuusbezorgd.driver.core.application.command;


public record CreateDeliveryCommand(Long orderId,
                                    String riderName) {
}
