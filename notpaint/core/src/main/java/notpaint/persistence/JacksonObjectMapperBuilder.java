package notpaint.persistence;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class for building a configured ObjectMapper.
 */
public class JacksonObjectMapperBuilder {

    /**
     * Get a configured ObjectMapper.
     *
     * @return Configured ObjectMapper
     */
    public static ObjectMapper getConfiguredObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Stop mapper from considering getXxx() and isXxx() methods for serialization
        mapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);

        return mapper;
    }
}
