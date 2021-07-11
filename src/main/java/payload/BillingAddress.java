package payload;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder({"email", "phone"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillingAddress extends AbstractAddress {
    private String email;
    private String phone;

    public BillingAddress() {
    }

    public BillingAddress(String first_name, String last_name, String company, String address_1, String address_2,
                          String city, String state, String postcode, String country, String email, String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.company = company;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
        this.email = email;
        this.phone = phone;
    }

    @JsonGetter("email")
    public String getEmail() {
        return email;
    }

    @JsonSetter("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonGetter("phone")
    public String getPhone() {
        return phone;
    }

    @JsonSetter("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
