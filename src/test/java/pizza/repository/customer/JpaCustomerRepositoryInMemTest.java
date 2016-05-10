package pizza.repository.customer;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repositoryTestContext.xml"})
public class JpaCustomerRepositoryInMemTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private Customer customer;
	
	@Before
	public void initMethod() {
		customer = new Customer("Customer name");
	}
	
	@Test
	public void testGetCustomerAndSaveCustomer() {
		customerRepository.saveCustomer(customer);
		Customer expected = customer;
		Customer result = customerRepository.getCustomer(customer.getId());
		assertEquals(expected, result);
		assertEquals(expected.getName(), result.getName());
	}
	
	@Test
	public void testUpdateCustomer() {
		customerRepository.saveCustomer(customer);
		String updatedName = "Updated name";
		Customer updatedCustomer = new Customer(updatedName);
		updatedCustomer.setId(customer.getId());
		customerRepository.updateCustomer(updatedCustomer);
		Customer expected = updatedCustomer;
		Customer result = customerRepository.getCustomer(customer.getId());
		assertEquals(expected, result);
		assertEquals(expected.getName(), result.getName());
	}
	
	@Test
	public void testDeleteCustomer() {
		customerRepository.saveCustomer(customer);
		boolean expected = true;
		boolean result = customerRepository.deleteCustomer(customer.getId());
		assertEquals(expected, result);
		
		expected = false;
		result = customerRepository.deleteCustomer(100500);
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetCustomerWithAddresses() {
		Address address1 = new Address("Kiev", "Kudryashova", "10", "5");
		Address address2 = new Address("Lviv", "Petrovska", "8", "99");
		customer.addAddress(address1);
		customer.addAddress(address2);
		customerRepository.saveCustomer(customer);
		Customer expected = customer;
		Customer result = customerRepository.getCustomerWithAddresses(customer.getId());
		assertEquals(expected.getAddresses().get(0), result.getAddresses().get(0));
		assertEquals(expected.getAddresses().get(1), result.getAddresses().get(1));
	}
	
	@Test
	public void testUpdateAddresses() {
		Address address = new Address("Kiev", "Kudryashova", "10", "5");
		customer.addAddress(address);
		customerRepository.saveCustomer(customer);
		List<Address> expected = customer.getAddresses();
		List<Address> result = customerRepository.getCustomerWithAddresses(customer.getId()).getAddresses();
		assertEquals(expected.get(0), result.get(0));
		
		Address updatedAddress = new Address("Kharkiv", "Polyova", "4-a", "45");
		customer.addAddress(updatedAddress);
		customerRepository.updateAddresses(customer); // not persistent new address
		expected = customer.getAddresses();
		result = customerRepository.getCustomerWithAddresses(customer.getId()).getAddresses();
		assertEquals(expected.get(0), result.get(0));
		assertEquals(expected.get(1), result.get(1));
	}

}
