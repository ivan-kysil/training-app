package ikysil.training.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;

public class TrainingAppObjectMapperBuilder extends Jackson2ObjectMapperBuilder {

    public TrainingAppObjectMapperBuilder() {
        final SimpleModule instants = new SimpleModule()
                .addDeserializer(Instant.class, new RejectNumericTimestampsDeserializer());
        final SimpleModule bigDecimals = new SimpleModule()
                .addSerializer(BigDecimal.class, new ToStringSerializer(BigDecimal.class));
        this
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modulesToInstall(bigDecimals, instants, new JavaTimeModule());
    }

    private static class RejectNumericTimestampsDeserializer extends JsonDeserializer<Instant> {

        @Override
        public Instant deserialize(JsonParser parser, DeserializationContext context)
                throws IOException {
            if (isNumericTimestamp(parser)) {
                throw new IllegalArgumentException("Numeric timestamp is not supported: " + parser.getValueAsString());
            }

            return InstantDeserializer.INSTANT.deserialize(parser, context);
        }

        private boolean isNumericTimestamp(JsonParser parser)
                throws IOException {
            return parser.getCurrentToken().isNumeric() || StringUtils.isNumeric(parser.getValueAsString());
        }
    }
}
