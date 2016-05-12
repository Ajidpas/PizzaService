package pizza.repository.customer;

import static org.junit.Assert.*;

import org.junit.Test;

import pizza.domain.customer.Customer;
import pizza.repository.Repository;

public class InMemCustomerRepositoryTest {
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	@Test
	public void testSaveCustomer() {
		Repository<Customer> repository = new InMemCustomerRepository();
		
		// check that repository is empty
		int expected = 0;
		int result = repository.getAll().size();
		assertEquals(expected, result);
		
		// add one customer get it and check
		Customer expectedCustomer = customer;
		repository.insert(customer);
		Customer resultCustomer = repository.getAll().get(0);
		assertEquals(expectedCustomer, resultCustomer);
		
		// add one else the same Customer
		repository.insert(customer);
		resultCustomer = repository.getAll().get(0);
		assertEquals(expectedCustomer, resultCustomer);
		resultCustomer = repository.getAll().get(1);
		assertEquals(expectedCustomer, resultCustomer);
	}

}
