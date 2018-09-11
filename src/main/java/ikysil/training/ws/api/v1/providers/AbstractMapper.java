package ikysil.training.ws.api.v1.providers;

import ikysil.training.ws.api.v1.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;


public abstract class AbstractMapper<E extends Throwable> implements javax.ws.rs.ext.ExceptionMapper<E> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public final Response toResponse(E exception) {
        logException(LOGGER, exception);
        return Response.serverError().entity(new ErrorDto()
                .setCode(getStatus().getStatusCode())
                .setStatus(getStatus().getReasonPhrase())
                .setMessage(getMessage(exception))
        ).type("application/json").build();
    }

    protected Response.Status getStatus() {
        return Response.Status.INTERNAL_SERVER_ERROR;
    }

    protected String getMessage(E e) {
        return "Unexpected exception while processing a request";
    }

    protected void logException(Logger log, E e) {
        log.error(getMessage(e), e);
    }

}