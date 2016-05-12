package pizza.service.orderservice;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pizza.domain.Pizza;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.InMemTest;
import pizza.service.OrderService;
import pizza.service.PizzaService;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public class SimpleOrderServiceInMemTest extends InMemTest {
	
	private Pizza pizza1 = new Pizza("Pizza 1", 100, Pizza.PizzaType.SEA);
	
	private Pizza pizza2 = new Pizza("Pizza 2", 200, Pizza.PizzaType.VEGETABLES);
	
	private Pizza pizza3 = new Pizza("Pizza 3", 300, Pizza.PizzaType.VEGETARIAN);
	
	private Address address = new Address("Kiev", "Kudriashova", "18", "1");
	
	private Customer customer = new Customer("Customer name", address);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@Before
	public void initMethod() {
		pizzaService.insertPizza(pizza1);
		pizzaService.insertPizza(pizza2);
		pizzaService.insertPizza(pizza3);
	}
	
	@Test
	public void testPlaceNewOrder() throws NotSupportedPizzasNumberException, NoSuchPizzaException, WrongStatusException {
		Order order = orderService.placeNewOrder(customer, address, pizza1.getId(), pizza2.getId(), pizza3.getId());
		List<Pizza> pizzas = getOrderPizzasFromDatabase(order);
		
		int expectedPizzasNumber = 3;
		int resultPizzasNumber = pizzas.size();
		assertEquals(expectedPizzasNumber, resultPizzasNumber);
	}
	
	@Test
	public void testAddPizzasIntoOrder() throws NotSupportedPizzasNumberException, NoSuchPizzaException, WrongStatusException {
		Order order = orderService.placeNewOrder(customer, address, pizza1.getId(), pizza2.getId());
		List<Pizza> pizzas = getOrderPizzasFromDatabase(order);
		int expected = 2;
		int result = pizzas.size();
		assertEquals(expected, result);
		
		orderService.addPizzasIntoOrder(order.getId(), pizza3.getId());
		pizzas = getOrderPizzasFromDatabase(order);
		expected = 3;
		result = pizzas.size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testDeletePizzaFromOrder() throws NotSupportedPizzasNumberException, NoSuchPizzaException, WrongStatusException {
		Order order = orderService.placeNewOrder(customer, address, pizza1.getId(), pizza2.getId(), pizza3.getId());
		List<Pizza> pizzas = getOrderPizzasFromDatabase(order);
		int expected = 3;
		int result = pizzas.size();
		assertEquals(expected, result);
		
		orderService.deletePizzasFromOrder(order.getId(), pizza3.getId());
		pizzas = getOrderPizzasFromDatabase(order);
		expected = 2;
		result = pizzas.size();
		assertEquals(expected, result);
	}

	private List<Pizza> getOrderPizzasFromDatabase(Order order) {
		String sql = "SELECT p.name, p.price, p.type FROM pizza p INNER JOIN order_pizza op ON p.pizza_id = op.pizza_id WHERE op.order_id = " + Integer.toString(order.getId());
		List<Pizza> pizzas = this.jdbcTemplate.query(sql, new RowMapper<Pizza>() {

			@Override
			public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
				Pizza pizza = new Pizza();
				pizza.setName(rs.getString("name"));
				pizza.setPrice(rs.getDouble("price"));
				pizza.setType(Pizza.PizzaType.valueOf(rs.getString("type").toUpperCase()));
				return pizza;
			}

		});
		return pizzas;
	}

}
