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
