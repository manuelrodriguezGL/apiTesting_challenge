package payload;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder({"id", "email", "first_name", "last_name", "username", "password", "billing", "shipping"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private BillingAddress billing;
    private ShippingAddress shipping;

    public Customer() {
        id = "";
        email = "";
        first_name = "";
        last_name = "";
        username = "";
        password = "";
        billing = new BillingAddress();
        shipping = new ShippingAddress();
    }

    public Customer(String email, String first_name, String last_name, String username, String password) {
        this.id = "";
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.billing = null;
        this.shipping = null;
    }

    @JsonGetter("id")
    public String getId() {
        return id;
    }

    /**
     * The ID is set by the API, no need to set this one ourselves.
     *
     * @return
     */
//    public void setId(String id) {
//        this.id = id;
//    }
    @JsonGetter("email")
    public String getEmail() {
        return email;
    }

    @JsonSetter("email")
    public void setEmail(String email) {
        this.email = email;
    }

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

    @JsonGetter("username")
    public String getUsername() {
        return username;
    }

    @JsonSetter("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    @JsonSetter("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonGetter("billing")
    public BillingAddress getBilling() {
        return billing;
    }

    @JsonSetter("billing")
    public void setBilling(BillingAddress billing) {
        this.billing = billing;
    }

    @JsonGetter("shipping")
    public ShippingAddress getShipping() {
        return shipping;
    }

    @JsonSetter("shipping")
    public void setShipping(ShippingAddress shipping) {
        this.shipping = shipping;
    }
}
