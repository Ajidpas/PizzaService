package pizza.web.converters;

import org.springframework.core.convert.converter.Converter;

import pizza.domain.Pizza.PizzaType;

public class PizzaTypeConverter implements Converter<String, PizzaType>{

	@Override
	public PizzaType convert(String pizzaType) {
		return PizzaType.valueOf(pizzaType);
	}

}
