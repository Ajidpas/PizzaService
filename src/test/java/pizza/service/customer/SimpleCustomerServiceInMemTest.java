package pizza.service.customer;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pizza.domain.Pizza;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.service.CustomerService;
import pizza.service.InMemTest;

import com.mysql.jdbc.Statement;

public class SimpleCustomerServiceInMemTest extends InMemTest {
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testGetAllCustomers() {
		insertCustomerAndGetId();
		insertCustomerAndGetId();
		insertCustomerAndGetId();
		List<Customer> customers = customerService.getAllCustomers();
		int expected = 3;
		int result = customers.size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testInsertCustomer() {
		Customer customer = new Customer("Customer name");
		customerService.insertCustomer(customer);
		Customer expected = customer;
		Customer result = getCustomerFromDatabase(customer.getId());
		assertEquals(expected.getName(), result.getName());
	}
	
	@Test
	public void getCustomer() {
        int id = insertCustomerAndGetId();
		Customer customer = customerService.getCustomer(id);
		assertNotNull(customer);
		assertNotNull(customer.getName());
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer customer = new Customer("Customer name");
		customerService.insertCustomer(customer);
		String newName = "Updated name";
		customer.setName(newName);
		customerService.updateCustomer(customer);
		String expected = newName;
		String result = customerService.getCustomer(customer.getId()).getName();
		assertEquals(expected, result);
	}
	
	@Test
	public void testDeleteCustomer() {
		int customerId = insertCustomerAndGetId();
		Customer customer = getCustomerFromDatabase(customerId);
		assertNotNull(customer);
		
		List<Customer> customers = customerService.getAllCustomers();
		int expected = 1;
		int result = customers.size();
		assertEquals(expected, result);
		
		customerService.deleteCustomer(customerId);
		customers = customerService.getAllCustomers();
		expected = 0;
		result = customers.size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testAddAddressToCustomer() {
		Customer customer = new Customer("Customer name");
		customerService.insertCustomer(customer);
		Address address1 = new Address("Kiev", "Kudryashova", "2", "14");
		Address address2 = new Address("Lviv", "Petrova", "8", "10");
		customerService.addAddressToCustomer(customer.getId(), address1);
		customerService.addAddressToCustomer(customer.getId(), address2);
		List<Address> addresses = getCustomerAddressesFromDatabase(customer);
		int expected = 2;
		int result = addresses.size();
		assertEquals(expected, result);
		
		Address address3 = new Address("Kharkiv", "Lepse", "7", "41");
		customerService.addAddressToCustomer(customer.getId(), address3);
		addresses = getCustomerAddressesFromDatabase(customer);
		expected = 3;
		result = addresses.size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetCustomerAddresses() {
		Customer customer = new Customer("Customer name");
		customerService.insertCustomer(customer);
		Address address1 = new Address("Kiev", "Kudryashova", "2", "14");
		Address address2 = new Address("Lviv", "Petrova", "8", "10");
		Address address3 = new Address("Kharkiv", "Lepse", "7", "41");
		System.out.println("Before: " + address3.getId());
		customerService.addAddressToCustomer(customer.getId(), address1);
		customerService.addAddressToCustomer(customer.getId(), address2);
		customerService.addAddressToCustomer(customer.getId(), address3);
		System.out.println("After: " + address3.getId());
		List<Address> addresses = customerService.getCustomerAddresses(customer.getId());
		int expected = 3;
		int result = addresses.size();
		assertEquals(expected, result);
	}

	private int insertCustomerAndGetId() {
		KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("INSERT INTO customer (name) VALUES ('Inserted customer')", Statement.RETURN_GENERATED_KEYS);
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
	}

	private Customer getCustomerFromDatabase(int customerId) {
		return this.jdbcTemplate.queryForObject("SELECT name FROM customer WHERE customer_id = ?", new Object[]{customerId}, new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Customer customer = new Customer();
				customer.setName(rs.getString("name"));
				return customer;
			}
			
		});
	}
	
	private List<Address> getCustomerAddressesFromDatabase(Customer customer) {
		String sql = "SELECT a.city, a.street, a.house, a.flat FROM address a INNER JOIN customer_address ca ON a.address_id = ca.address_id WHERE ca.customer_id = " + Integer.toString(customer.getId());
		List<Address> addresses = this.jdbcTemplate.query(sql, new RowMapper<Address>() {

			@Override
			public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
				Address address = new Address();
				address.setCity(rs.getString("city"));
				address.setStreet(rs.getString("street"));
				address.setHouse(rs.getString("house"));
				address.setFlat(rs.getString("flat"));
				return address;
			}

		});
		return addresses;
	}

}
