package notpaint.restserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import notpaint.persistence.JacksonObjectMapperBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures Jackson for the REST server.
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return JacksonObjectMapperBuilder.getConfiguredObjectMapper();
    }
}