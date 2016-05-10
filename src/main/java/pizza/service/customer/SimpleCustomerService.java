package pizza.service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.repository.CustomerRepository;
import pizza.service.CustomerService;

@Service
public class SimpleCustomerService implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer getCustomerWithAddresses(int customeerId) {
		return customerRepository.getCustomerWithAddresses(customeerId);
	}
	
	@Override
	public void insertCustomer(Customer customer) {
		customerRepository.saveCustomer(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		return customerRepository.getCustomer(id);
	}

	@Override
	public Customer updsteCustomer(Customer newCustomer, int id) {
		newCustomer.setId(id);
		return customerRepository.updateCustomer(newCustomer);
	}

	@Override
	public void deleteCustomer(int id) {
		customerRepository.deleteCustomer(id);
	}

	@Override
	public void addAddressToCustomer(int customerId, Address newAddress) {
		Customer customer = customerRepository.getCustomerWithAddresses(customerId);
		List<Address> addresses = customer.getAddresses();
		addresses.add(newAddress);
		customerRepository.updateAddresses(customer);
	}

}
