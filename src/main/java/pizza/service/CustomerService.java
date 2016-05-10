package pizza.service;

import java.util.List;

import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;

public interface CustomerService {
	
	void insertCustomer(Customer customer);
	
	Customer getCustomer(int id);
	
	Customer updsteCustomer(Customer newCustomer, int id);
	
	void deleteCustomer(int id);

	Customer getCustomerWithAddresses(int customeerId);
	
	void addAddressToCustomer(int customerId, Address newAddress);

}
