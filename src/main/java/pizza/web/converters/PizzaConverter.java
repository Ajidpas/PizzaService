package pizza.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.PizzaService;

public class PizzaConverter implements Converter<String, Pizza> {
	
	@Autowired
	private PizzaService pizzaService;

	@Override
	public Pizza convert(String pizzaId) {
		Integer id = Integer.valueOf(pizzaId);
		Pizza pizza = null;
		try {
			pizza = pizzaService.getPizza(id);
		} catch (NoSuchPizzaException e) {
			return null;
		}
		return pizza;
	}

}
