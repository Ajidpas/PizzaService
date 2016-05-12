package pizza.service.pizza;

import static org.junit.Assert.*;

import java.sql.*;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.service.InMemTest;
import pizza.service.PizzaService;

public class SimplePizzaServiceInMemTest extends InMemTest {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Test
	public void testgetAll() {
		insertPizzaAndGetId();
		insertPizzaAndGetId();
		insertPizzaAndGetId();
		int expected = 3;
		int result = pizzaService.getAllPizzas().size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testInsertPizza() {
		Pizza pizza1 = new Pizza("Pizza 1", 100, Pizza.PizzaType.SEA);
		Pizza pizza2 = new Pizza("Pizza 2", 200, Pizza.PizzaType.VEGETABLES);
		Pizza pizza3 = new Pizza("Pizza 3", 300, Pizza.PizzaType.VEGETARIAN);
		pizzaService.insertPizza(pizza1);
		pizzaService.insertPizza(pizza2);
		pizzaService.insertPizza(pizza3);
		int expected = 3;
		int result = pizzaService.getAllPizzas().size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testUpdatePizza() {
		Pizza pizza = new Pizza("Pizza 1", 100, Pizza.PizzaType.SEA);
		pizzaService.insertPizza(pizza);
		String newName = "New name";
		double newPrice = 100500;
		PizzaType newType = PizzaType.MEAT;
		pizza.setName(newName);
		pizza.setPrice(newPrice);
		pizza.setType(newType);
		pizzaService.updatePizza(pizza);
		Pizza result = getPizzaFromDatabase(pizza.getId());
		assertEquals(newName, result.getName());
		assertEquals(newPrice, result.getPrice(), 0.0001);
		assertEquals(newType, result.getType());
	}
	
	@Test
	public void testDeletePizza() {
		int pizzaId = insertPizzaAndGetId();
		insertPizzaAndGetId();
		insertPizzaAndGetId();
		int expected = 3;
		int result = getAllPizzasFromDatabase().size();
		assertEquals(expected, result);
		
		pizzaService.deletePizza(pizzaId);
		expected = 2;
		result = getAllPizzasFromDatabase().size();
		assertEquals(expected, result);
	}

	private Pizza getPizzaFromDatabase(Integer pizzaid) {
		return this.jdbcTemplate.queryForObject("SELECT name, price, type FROM pizza WHERE pizza_id = ?", new Object[]{pizzaid}, new RowMapper<Pizza>() {

			@Override
			public Pizza mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Pizza pizza = new Pizza();
				pizza.setName(rs.getString("name"));
				pizza.setPrice(rs.getDouble("price"));
				pizza.setType(Pizza.PizzaType.valueOf(rs.getString("type").toUpperCase()));
				return pizza;
			}
			
		});
	}
	
	private int insertPizzaAndGetId() {
		KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("INSERT INTO pizza (name, price, type) VALUES ('Pizza 1', 100, 'SEA')", Statement.RETURN_GENERATED_KEYS);
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
	}
	
	private List<Pizza> getAllPizzasFromDatabase() {
		String sql = "SELECT name, price, type FROM pizza";
		List<Pizza> pizzas = this.jdbcTemplate.query(sql, new RowMapper<Pizza>() {

			@Override
			public Pizza mapRow(ResultSet rs, int rowNum)
					throws SQLException {
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
