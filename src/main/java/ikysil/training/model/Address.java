package ikysil.training.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
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
