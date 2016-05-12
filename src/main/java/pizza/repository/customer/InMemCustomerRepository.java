package pizza.repository.customer;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.customer.Customer;
import pizza.repository.Repository;

public class InMemCustomerRepository implements Repository<Customer> {

private List<Customer> customers;

	@Override
	public List<Customer> getAll() {
		if (customers == null) {
			customers = new ArrayList<Customer>();
		}
		return customers;
	}
	
	public Customer insert(Customer customer) {
		if (customers == null) {
			customers = new ArrayList<Customer>();
		}
		customers.add(customer);
		return customer;
	}

	@Override
	public Customer get(int id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Customer update(Customer customer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(int id) {
		throw new UnsupportedOperationException();
	}
	
}
