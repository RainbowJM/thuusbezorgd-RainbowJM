package nl.hu.inno.thuusbezorgd.orders.adapters.in.message.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

import java.time.Instant;
import java.util.Random;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "eventKey"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeliveryCreatedEvent.class),
        @JsonSubTypes.Type(value = DeliveryUpdatedEvent.class),
})
public abstract class DeliveryEvent {
    @Getter
    private  final Long id = new Random().nextLong();

    @Getter
    private final Instant timestamp = Instant.now();

    public abstract String getEventKey();
}
