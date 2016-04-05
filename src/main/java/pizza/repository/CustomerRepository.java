package pizza.repository;

import pizza.domain.customer.Customer;

public interface CustomerRepository {
	
	public Long saveCustomer(Customer customer);

}
