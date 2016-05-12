package pizza.repository.customer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pizza.domain.customer.Customer;
import pizza.repository.Repository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repositoryTestContext.xml"})
public class JpaCustomerRepositoryInMemTest {
	
	@Autowired
	private Repository<Customer> customerRepository;
	
	private Customer customer;
	
	@Before
	public void initMethod() {
		customer = new Customer("Customer name");
	}
	
	@Test 
	public void getAllCustomers() {
		customerRepository.insert(customer);
		customerRepository.getAll();
	}
	
	@Test
	public void testGetCustomerAndSaveCustomer() {
		customerRepository.insert(customer);
		Customer expected = customer;
		Customer result = customerRepository.get(customer.getId());
		assertEquals(expected, result);
		assertEquals(expected.getName(), result.getName());
	}
	
	@Test
	public void testUpdateCustomer() {
		customerRepository.insert(customer);
		String updatedName = "Updated name";
		Customer updatedCustomer = new Customer(updatedName);
		updatedCustomer.setId(customer.getId());
		customerRepository.update(updatedCustomer);
		Customer expected = updatedCustomer;
		Customer result = customerRepository.get(customer.getId());
		assertEquals(expected, result);
		assertEquals(expected.getName(), result.getName());
	}
	
	@Test
	public void testDeleteCustomer() {
		customerRepository.insert(customer);
		boolean expected = true;
		boolean result = customerRepository.delete(customer.getId());
		assertEquals(expected, result);
		
		expected = false;
		result = customerRepository.delete(100500);
		assertEquals(expected, result);
	}

}
