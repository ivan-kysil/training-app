package ikysil.training.ws.api.v1.providers;

import com.fasterxml.jackson.databind.JsonMappingException;

import javax.annotation.Priority;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


@Provider
@Priority(1)
public class JsonMappingExceptionMapper extends AbstractMapper<JsonMappingException> {

    @Override
    protected Response.Status getStatus() {
        return Response.Status.BAD_REQUEST;
    }

    @Override
    protected String getMessage(JsonMappingException e) {
        return "Was unable to deserialize JSON. Check request body you pass.";
    }
}