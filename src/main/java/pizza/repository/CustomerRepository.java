package pizza.repository;

import java.util.List;

import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;

public interface CustomerRepository {

	List<Customer> getCustomers();
	
	Customer saveCustomer(Customer customer);
	
	Customer getCustomer(int id);
	
	Customer updateCustomer(Customer customer);
	
	boolean deleteCustomer(int id);

	Customer getCustomerWithAddresses(int customerId);

	void updateAddresses(Customer customer);

	void insertAddress(Address address);

}
