package pizza.repository.customer;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.repository.CustomerRepository;

public class InMemCustomerRepository implements CustomerRepository {

private List<Customer> customers;
	
	public Customer saveCustomer(Customer customer) {
		if (customers == null) {
			customers = new ArrayList<Customer>();
		}
		customers.add(customer);
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {
		if (customers == null) {
			customers = new ArrayList<Customer>();
		}
		return customers;
	}

	@Override
	public Customer getCustomer(int id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteCustomer(int id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Customer getCustomerWithAddresses(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAddresses(Customer customer) {
		throw new UnsupportedOperationException();
	}
	
}
