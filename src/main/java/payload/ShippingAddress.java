package payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShippingAddress extends AbstractAddress {
    public ShippingAddress() {
    }

    /**
     * Unlike Billing Address, this class does not have any additional field than those defined on the abstract class
     * Still, I want to explicitly differentiate objects
     */
    public ShippingAddress(String first_name, String last_name, String company, String address_1, String address_2,
                           String city, String postcode, String country, String state) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.company = company;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
        this.state = state;
    }
}
