package pizza.repository;

import java.util.List;

import pizza.domain.customer.Customer;

public interface CustomerRepository {
	
	public Long saveCustomer(Customer customer);
	
	public List<Customer> getCustomers();

}
