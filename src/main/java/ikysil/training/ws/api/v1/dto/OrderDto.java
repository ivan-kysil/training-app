package ikysil.training.ws.api.v1.dto;

import ikysil.training.model.Order;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class OrderDto {

    private String id;

    @NotEmpty(message = "orderNumber must not be null")
    private String orderNumber;
    private String sourceSystem;

    @NotEmpty(message = "userId must not be null")
    private String userId;
    @NotEmpty(message = "userEmail must not be null")
    @Email(message = "userEmail must be valid email format")
    private String userEmail;

    private DeliveryDto deliveryInfo;
    private OrderStatus orderStatus;

    private Instant createdOn;
    private Instant updatedOn;
    private Instant completedOn;

    public OrderDto() { }

    public OrderDto(String id, String orderNumber, String sourceSystem, String userId, String userEmail) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.sourceSystem = sourceSystem;
        this.userId = userId;
        this.userEmail = userEmail;
    }

    public Order toModel() {
        // TODO fill model from dto
        return new Order();
    }
}
