package payload;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"id", "email", "first_name", "last_name", "username", "password", "billing", "shipping"})
public class Customer {

    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private List<BillingAddress> billing;
    private List<ShippingAddress> shipping;

    public Customer() {
        id = "";
        email = "";
        first_name = "";
        last_name = "";
        username = "";
        password = "";
        billing = new ArrayList<>();
        shipping = new ArrayList<>();
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

//    @JsonGetter("password")
//    public String getPassword() {
//        return password;
//    }

    @JsonSetter("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonGetter("billing")
    public List<BillingAddress> getBilling() {
        return billing;
    }

    @JsonSetter("billing")
    public void setBilling(List<BillingAddress> billing) {
        this.billing = billing;
    }

    @JsonGetter("shipping")
    public List<ShippingAddress> getShipping() {
        return shipping;
    }

    @JsonSetter("shipping")
    public void setShipping(List<ShippingAddress> shipping) {
        this.shipping = shipping;
    }
}
