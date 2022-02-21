package comparators;

import payload.BillingAddress;
import payload.Customer;

public class CustomerComparator {
    public boolean compareCustomer(Customer customer, String email, String first_name, String last_name, String username,
                                   String password) {
        boolean result = customer.getEmail().equals(email) &&
                customer.getFirst_name().equals(first_name) &&
                customer.getLast_name().equals(last_name) &&
                customer.getUsername().equals(username) &&
                customer.getPassword().equals(password);
        return result;

    }

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
}
