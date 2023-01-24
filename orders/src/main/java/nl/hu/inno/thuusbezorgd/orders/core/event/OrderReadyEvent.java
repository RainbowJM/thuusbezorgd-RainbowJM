package nl.hu.inno.thuusbezorgd.orders.core.event;

public class OrderReadyEvent extends OrderEvent {
    public static final String KEY = "order.event.ready";

    @Override
    public String getEventKey() {
        return KEY;
    }
}
