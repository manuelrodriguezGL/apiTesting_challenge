package payload;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * This class exemplifies the use of an abstract parent, and how the JSON parser knows which son it belongs to
 * We want to ignore on purpose some JSON properties that come from API response, that's why I added
 * JsonIgnoreProperties annotation
 */

@JsonPropertyOrder({"first_name", "last_name", "company", "address_1", "address_2", "city", "postcode", "country", "state"})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractAddress {
    protected String first_name;
    protected String last_name;
    protected String company;
    protected String address_1;
    protected String address_2;
    protected String city;
    protected String postcode;
    protected String country;
    protected String state;

    @JsonGetter("first_name")
    public String getFirst_name() {
        return first_name;
    }

    @JsonSetter("first_name")
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @JsonGetter("last_name")
    public String getLast_name() {
        return last_name;
    }

    @JsonSetter("last_name")
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @JsonGetter("company")
    public String getCompany() {
        return company;
    }

    @JsonSetter("company")
    public void setCompany(String company) {
        this.company = company;
    }

    @JsonGetter("address_1")
    public String getAddress_1() {
        return address_1;
    }

    @JsonSetter("address_1")
    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    @JsonGetter("address_2")
    public String getAddress_2() {
        return address_2;
    }

    @JsonSetter("address_2")
    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    @JsonGetter("city")
    public String getCity() {
        return city;
    }

    @JsonSetter("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonGetter("postcode")
    public String getPostcode() {
        return postcode;
    }

    @JsonSetter("postcode")
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @JsonGetter("country")
    public String getCountry() {
        return country;
    }

    @JsonSetter("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonGetter("state")
    public String getState() {
        return state;
    }

    @JsonSetter("state")
    public void setState(String state) {
        this.state = state;
    }
}
