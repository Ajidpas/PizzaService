package pizza.repository;

import java.util.List;

import pizza.domain.customer.Customer;

public interface CustomerRepository {

	List<Customer> getCustomers();
	
	Customer saveCustomer(Customer customer);
	
	Customer getCustomer(int id);
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomer(int id);

}
