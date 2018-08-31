package ikysil.training.ws.api.v1.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String firstName;
    private String lastName;
    private String companyName;
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String countryCode;
    private String state;
    private String postalCode;

}
