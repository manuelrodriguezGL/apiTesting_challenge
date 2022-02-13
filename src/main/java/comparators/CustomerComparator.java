package comparators;

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
}
