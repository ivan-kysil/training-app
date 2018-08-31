package ikysil.training.ws.api.v1.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DeliveryDto {

    private AddressDto shippingAddress;
    private String deliveryEmail;
}
