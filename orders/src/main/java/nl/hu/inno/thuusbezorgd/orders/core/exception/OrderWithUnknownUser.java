package nl.hu.inno.thuusbezorgd.orders.core.exception;

public class OrderWithUnknownUser extends RuntimeException {
    public OrderWithUnknownUser() {
        super("Couldn't create an order with that unknown user.");
    }
}
