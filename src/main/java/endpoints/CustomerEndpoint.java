package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.BillingAddress;
import payload.Customer;
import payload.ShippingAddress;

import java.util.List;


public class CustomerEndpoint extends BaseEndpoint {

    private String endpointPath;

    public CustomerEndpoint(String baseUrl) {
        super(baseUrl);
        endpointPath = baseUrl;
    }

    /**
     * Gets a Customer by ID
     *
     * @param customerId Customer ID
     * @return An HTTP Response object with customer inside a JSON
     */
    public Response getCustomerByID(String customerId) {
        requestSpecification.pathParams("id", customerId);
        return requestSpecification.given().when().get(endpointPath + "/{id}");
    }

    /**
     * Gets a subset of all Customers from API, using default API sorting
     *
     * @param quantity Quantity of customers to get
     * @param userRole The role of the user, as required by the API
     * @return An HTTP Response object with all customers inside a JSON
     * @throws Exception
     */
    public Response getCustomerByQuantity(int quantity, String userRole)
            throws Exception {

        String endpointPath = buildEndpointPath(endpointRoutes.CUSTOMER_PATH);
        requestSpecification.queryParams("per_page", quantity, "role", userRole);

        return requestSpecification.given().when().get(endpointPath);
    }

    /**
     * Gets all customers from API, following an order (ascending or descending), and a order by criteria
     *
     * @param order    Order of sort criteria: ascending or descending
     * @param orderBy  Order by criteria: Field to order the customers by
     * @param userRole The role of the user, as required by the API
     * @return An HTTP Response object with all customers inside a JSON
     */
    public Response getCustomersSorted(String order, String orderBy, String userRole) throws Exception {
        String endpointPath = buildEndpointPath(endpointRoutes.CUSTOMER_PATH);
        requestSpecification.queryParams("order", order, "orderby", orderBy, "role", userRole);

        return requestSpecification.given().when().get(endpointPath);
    }

    /**
     * Creates a new customer with basic information
     *
     * @param email      Customer's email address
     * @param first_name Customer's first name
     * @param last_name  Customer's last name
     * @param username   Customer's user name
     * @param password   Customer's password
     * @return An HTTP Response object with all customer data inside a JSON
     */
    @SuppressWarnings("unchecked")
    public Response postCustomer(String email, String first_name, String last_name, String username,
                                 String password, String token) {

        // The API expects URL encoded data. Since JSON library doesn't have a way to serialize that,
        // I delegate that into RestAssured, which needs an Object to build the form params
        requestSpecification.formParams(commonUtils.objectToMap(
                new Customer("", email, first_name, last_name, username, password)));

        return requestSpecification.given()
                .contentType(ContentType.URLENC)
                .when()
                .post(endpointPath);
    }

    /**
     * Updates the customer with new billing address data
     *
     * @param customerId Customer ID to update
     * @param first_name Customer's first name
     * @param last_name  Customer's last name
     * @param company    Customer's company name
     * @param address_1  Customer's address line 1
     * @param address_2  Customer's address line 2
     * @param city       Customer's city
     * @param state      Customer's state
     * @param postcode   Customer's postal code
     * @param country    Customer's country
     * @param email      Customer's email
     * @param phone      Customer's phone number
     * @return An HTTP Response object with all customer data inside a JSON
     * @throws Exception
     */
    public Response putCustomerBillingAddress(String customerId, String first_name, String last_name, String company,
                                              String address_1, String address_2, String city, String state,
                                              String postcode, String country, String email, String phone)
            throws Exception {
        requestSpecification.pathParams("id", customerId);

        // The request body expects a JSON object, enclosed inside another object with the label 'billing'
        // So I build that object manually using a custom serializer
        requestSpecification.body(commonUtils.objectToJsonString(new BillingAddress(first_name, last_name, company,
                        address_1, address_2, city, state, postcode, country, email, phone),
                "{ \"billing\" : %s}"));

        return requestSpecification.given()
                .contentType(ContentType.JSON)
                .when()
                .put(endpointPath + "/{id}");
    }

    /**
     * Patches existing customer shipping address
     *
     * @param customerId Customer ID to update
     * @param first_name Customer's first name
     * @param last_name  Customer's last name
     * @param company    Customer's company name
     * @param address_1  Customer's address line 1
     * @param address_2  Customer's address line 2
     * @param city       Customer's city
     * @param postcode   Customer's postal code
     * @param country    Customer's country
     * @param state      Customer's state
     * @return An HTTP Response object with all customer data inside a JSON
     * @throws Exception
     */
    public Response patchCustomerShippingAddress(String customerId, String first_name, String last_name, String company,
                                                 String address_1, String address_2, String city, String postcode,
                                                 String country, String state)
            throws Exception {
        requestSpecification.pathParams("id", customerId);

        // The request body expects a JSON object, enclosed inside another object with the label 'shipping'
        // So I build that object manually using a custom serializer
        requestSpecification.body(commonUtils.objectToJsonString(new ShippingAddress(first_name, last_name, company,
                        address_1, address_2, city, postcode, country, state),
                "{ \"shipping\" : %s}"));

        return requestSpecification.given()
                .contentType(ContentType.JSON)
                .when()
                .patch(endpointPath + "/{id}");
    }

    /**
     * Forces the deletion of a customer by ID
     *
     * @param customerId Customer ID
     * @return An HTTP Response object with all customer data inside a JSON
     * @throws Exception
     */
    public Response deleteCustomerByID(String customerId) throws Exception {

        requestSpecification.pathParams("id", customerId);

        // We force the deletion of the resource, as required by the API
        requestSpecification.queryParams("force", Boolean.TRUE.toString());

        return requestSpecification.given().when().delete(endpointPath + "/{id}");
    }

    /**
     * Returns a sorted list of customers
     *
     * @param order    Order of sort criteria: ascending or descending
     * @param orderBy  Order by criteria: Field to order the customers by
     * @param userRole The role of the user, as required by the API
     * @return A sorted list of HTTP Responses with customer data inside a JSON
     * @throws Exception
     */
    public List<Customer> getCustomerList(String order, String orderBy, String userRole) throws Exception {
        return getCustomersSorted(order, orderBy, userRole).jsonPath().getList("", Customer.class);
    }

    /**
     * Gets the last added customer
     *
     * @param order    Order of sort criteria: ascending or descending
     * @param orderBy  Order by criteria: Field to order the customers by
     * @param userRole The role of the user, as required by the API
     * @return A String with last added customer ID
     * @throws Exception
     */
    public String getLastCustomerID(String order, String orderBy, String userRole) throws Exception {
        // Since we need the last customer, we force the 'desc' value
        List<Customer> customers = getCustomerList("desc", orderBy, userRole);
        return customers.get(0).getId();
    }
}
