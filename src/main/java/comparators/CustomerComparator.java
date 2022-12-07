package comparators;

import payload.BillingAddress;
import payload.Customer;
import payload.ShippingAddress;

/**
 * This class was made to compare objects returned by API calls against test parameters
 * As a way to exemplofy the use of custom comparators.
 */
public class CustomerComparator {

    /**
     * Compare a Customer object with parameter values
     * @param customer A customer object
     * @param email Customer email
     * @param first_name Customer first name
     * @param last_name Customer last name
     * @param username Customer username
     * @param password Customer password
     * @return True if all values match, false otherwise
     */
    public boolean compareCustomer(Customer customer, String email, String first_name, String last_name, String username,
                                   String password) {
        boolean result = customer.getEmail().equals(email) &&
                customer.getFirst_name().equals(first_name) &&
                customer.getLast_name().equals(last_name) &&
                customer.getUsername().equals(username) &&
                customer.getPassword().equals(password);
        return result;

    }

    /**
     * Compare a Billing Address object with parameter values
     * @param billingAddress A Billing Address object
     * @param first_name Address owner first name
     * @param last_name Address owner last name
     * @param company Address company
     * @param address_1 Address line 1
     * @param address_2 Address line 2
     * @param city Address city name
     * @param state Address state name
     * @param postcode Address postal code
     * @param country Address country
     * @param email Address owner email
     * @param phone Address owner phone
     * @return True if all values match, false otherwise
     */
    public boolean compareBillingAddress(BillingAddress billingAddress,
                                         String first_name, String last_name, String company,
                                         String address_1, String address_2, String city, String state,
                                         String postcode, String country, String email, String phone) {
        boolean result = billingAddress.getFirst_name().equals(first_name) &&
                billingAddress.getLast_name().equals(last_name) &&
                billingAddress.getCompany().equals(company) &&
                billingAddress.getAddress_1().equals(address_1) &&
                billingAddress.getAddress_2().equals(address_2) &&
                billingAddress.getCity().equals(city) &&
                billingAddress.getState().equals(state) &&
                billingAddress.getPostcode().equals(postcode) &&
                billingAddress.getCountry().equals(country) &&
                billingAddress.getEmail().equals(email) &&
                billingAddress.getPhone().equals(phone);

        return result;
    }

    /**
     * Compare a Shipping Address object with parameter values
     * @param shippingAddress A Shipping Address object
     * @param first_name Address owner first name
     * @param last_name Address owner last name
     * @param company Address company
     * @param address_1 Address line 1
     * @param address_2 Address line 2
     * @param city Address city name
     * @param state Address state name
     * @param postcode Address postal code
     * @param country Address country
     * @return True if all values match, false otherwise
     */
    public boolean compareShippingAddress(ShippingAddress shippingAddress,
                                          String first_name, String last_name, String company,
                                          String address_1, String address_2, String city, String state,
                                          String postcode, String country) {
        boolean result = shippingAddress.getFirst_name().equals(first_name) &&
                shippingAddress.getLast_name().equals(last_name) &&
                shippingAddress.getCompany().equals(company) &&
                shippingAddress.getAddress_1().equals(address_1) &&
                shippingAddress.getAddress_2().equals(address_2) &&
                shippingAddress.getCity().equals(city) &&
                shippingAddress.getPostcode().equals(postcode) &&
                shippingAddress.getCountry().equals(country) &&
                shippingAddress.getState().equals(state);

        return result;
    }
}
