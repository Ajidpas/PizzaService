package pizza.service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.repository.Repository;
import pizza.service.CustomerService;

@Service
@Transactional
public class SimpleCustomerService implements CustomerService {
	
	@Autowired
	private Repository<Customer> customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.getAll();
	}
	
	@Override
	public Customer insertCustomer(Customer customer) {
		return customerRepository.insert(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		return customerRepository.get(id);
	}

	@Override
	public Customer updateCustomer(Customer newCustomer) {
		return customerRepository.update(newCustomer);
	}

	@Override
	public boolean deleteCustomer(int id) {
		return customerRepository.delete(id);
	}
	
	@Override
	public List<Address> getCustomerAddresses(int customerId) {
		Customer customer = customerRepository.get(customerId);
		customer.getAddresses().size();
		return customer.getAddresses();
	}

	@Override
	public void addAddressToCustomer(int customerId, Address newAddress) {
		Customer customer = customerRepository.get(customerId);
		customer.getAddresses();
		customer.addAddress(newAddress);
		customerRepository.update(customer);
	}

}
