package nl.hu.inno.thuusbezorgd.driver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.thuusbezorgd.driver.adapters.out.event.EventPublisher;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${message.project-exchange}")
    private String exchange;

    @Value("${message.queue.stock-event}")
    private String stockQueue;
    @Value("${message.queue.stock-binding}")
    private String stockBinding;

    @Value("${message.queue.order-event}")
    private String orderQueue;
    @Value("${message.queue.order-binding}")
    private String orderBinding;

    @Bean
    public Queue stockQueue() {
        return QueueBuilder.durable(stockQueue).build();
    }

    @Bean
    public Binding stockBinding(Queue stockQueue, TopicExchange exchange) {
        return BindingBuilder.bind(stockQueue).to(exchange).with(stockBinding);
    }

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(orderQueue).build();
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange exchange){
        return BindingBuilder.bind(orderQueue).to(exchange).with(orderBinding);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(exchange);
    }
    @Bean
    public ConnectionFactory connectionFactory(){
        return new CachingConnectionFactory(host, port);
    }

    @Bean
    public EventPublisher driverEventPublisher(RabbitTemplate template) {
        return new EventPublisher(exchange, template);
    }
    // Setup RabbitMQ communication
    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    // Configures a message converter so that it can be used by RabbitTemplate, format: JSON
    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        // Prevent deserialization using the sender-specific
        converter.setAlwaysConvertToInferredType(true);
        return converter;
    }

}
