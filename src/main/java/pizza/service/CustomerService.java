package pizza.service;

import java.util.List;

import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;

public interface CustomerService {
	
	Customer insertCustomer(Customer customer);
	
	Customer getCustomer(int id);
	
	Customer updateCustomer(Customer newCustomer);
	
	boolean deleteCustomer(int id);

	List<Address> getCustomerAddresses(int customeerId);
	
	void addAddressToCustomer(int customerId, Address newAddress);

	List<Customer> getAllCustomers();

}
