package ikysil.training.model;


import ikysil.training.ws.api.v1.dto.OrderDto;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private long id;
    private String orderNumber;
    private String sourceSystem;
    private String userId;
    private String userEmail;
    @Embedded
    private Delivery deliveryInfo;
    private OrderStatus orderStatus;
    private Instant createdOn;
    private Instant updatedOn;
    private Instant completedOn;


    public OrderDto toDto() {
        // TODO fill dto from model
        return new OrderDto();
    }
}
