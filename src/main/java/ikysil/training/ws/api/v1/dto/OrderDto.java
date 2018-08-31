package ikysil.training.ws.api.v1.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class OrderDto {

    private String id;
    private String orderNumber;
    private String sourceSystem;

    private String userId;
    private String userEmail;

    private DeliveryDto deliveryInfo;
    private OrderStatus orderStatus;

    private Instant createdOn;
    private Instant updatedOn;
    private Instant completedOn;
}
