package ikysil.training.ws.api.v1.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DeliveryDto {

    private AddressDto shippingAddress;
    private String deliveryEmail;

    public DeliveryDto(){
    }

    public DeliveryDto(String deliveryEmail){
//        this.shippingAddress = shippingAddress;
        this.deliveryEmail = deliveryEmail;
    }

    @Override
    public String toString() {
        return '{' +
                "deliveryEmail=" + deliveryEmail +
                '}';
    }
}
