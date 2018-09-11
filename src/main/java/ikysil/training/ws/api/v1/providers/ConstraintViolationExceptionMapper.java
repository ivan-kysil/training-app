package ikysil.training.ws.api.v1.providers;

import javax.annotation.Priority;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;


@Provider
@Priority(1)
public class ConstraintViolationExceptionMapper extends AbstractMapper<ConstraintViolationException> {

    @Override
    protected Response.Status getStatus() {
        return Response.Status.BAD_REQUEST;
    }

    @Override
    protected String getMessage(ConstraintViolationException e) {
        return "Validation error. " +
                e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));
    }
}