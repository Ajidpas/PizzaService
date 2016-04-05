package pizza.repository.customer;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.customer.Customer;
import pizza.repository.CustomerRepository;

public class InMemCustomerRepository implements CustomerRepository {

private List<Customer> customers;
	
	public Long saveCustomer(Customer customer) {
		if (customers == null) {
			customers = new ArrayList<Customer>();
		}
		customers.add(customer);
		return customer.getId();
	}
	
}
