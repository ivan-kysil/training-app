package ikysil.training.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Delivery {
    @Embedded
    private Address address;
    private String deliveryEmail;
}
