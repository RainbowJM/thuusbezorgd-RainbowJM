package nl.hu.inno.thuusbezorgd.driver.core.exception;

public class DeliveryNotFound extends RuntimeException {

    public DeliveryNotFound(Long deliveryId) {
        super(String.format("Delivery %s is invalid", deliveryId));
    }

}
