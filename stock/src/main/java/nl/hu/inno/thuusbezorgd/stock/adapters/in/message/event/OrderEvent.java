package nl.hu.inno.thuusbezorgd.stock.adapters.in.message.event;

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
        @JsonSubTypes.Type(value = OrderCreatedEvent.class),
        @JsonSubTypes.Type(value = OrderDeletedEvent.class),
})
public abstract class OrderEvent {
    @Getter
    private final Long id = new Random().nextLong();
    @Getter
    private final Instant timestamp = Instant.now();

    public abstract String getEventKey();
}
