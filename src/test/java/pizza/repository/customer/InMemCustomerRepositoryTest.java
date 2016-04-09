package pizza.repository.customer;

import static org.junit.Assert.*;

import org.junit.Test;

import pizza.domain.customer.Customer;
import pizza.repository.CustomerRepository;

public class InMemCustomerRepositoryTest {
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	@Test
	public void testSaveCustomer() {
		CustomerRepository repository = new InMemCustomerRepository();
		
		// check that repository is empty
		int expected = 0;
		int result = repository.getCustomers().size();
		assertEquals(expected, result);
		
		// add one customer get it and check
		Customer expectedCustomer = customer;
		repository.saveCustomer(customer);
		Customer resultCustomer = repository.getCustomers().get(0);
		assertEquals(expectedCustomer, resultCustomer);
		
		// add one else the same Customer
		repository.saveCustomer(customer);
		resultCustomer = repository.getCustomers().get(0);
		assertEquals(expectedCustomer, resultCustomer);
		resultCustomer = repository.getCustomers().get(1);
		assertEquals(expectedCustomer, resultCustomer);
	}

}
