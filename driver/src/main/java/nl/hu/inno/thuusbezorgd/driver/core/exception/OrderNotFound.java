package nl.hu.inno.thuusbezorgd.driver.core.exception;

public class OrderNotFound extends RuntimeException{
    public OrderNotFound(Long orderId){
        super(String.format("Order with id %s does not exist.", orderId));
    }
}