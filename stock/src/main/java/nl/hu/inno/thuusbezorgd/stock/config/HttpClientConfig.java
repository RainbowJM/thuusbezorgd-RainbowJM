package nl.hu.inno.thuusbezorgd.stock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${thuusbezorgd.rest.driver-path}")
    private String driverEndpointPath;

    @Value("${thuusbezorgd.rest.order-path}")
    private String orderEndpointPath;

    @Value("${thuusbezorgd.rest.stock-path}")
    private String stockEndpointPath;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
