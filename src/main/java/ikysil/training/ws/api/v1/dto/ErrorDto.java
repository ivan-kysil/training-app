package ikysil.training.ws.api.v1.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ErrorDto {
    private int code;
    private String status;
    private String message;


}
