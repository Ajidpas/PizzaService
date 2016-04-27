package pizza.infrastructure;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import pizza.domain.Pizza.PizzaType;

@Converter
public class PizzaTypeConverter implements AttributeConverter<PizzaType, String> {

	@Override
	public String convertToDatabaseColumn(PizzaType type) {
		return type.name();
	}

	@Override
	public PizzaType convertToEntityAttribute(String typeName) {
		return PizzaType.valueOf(typeName.toUpperCase());
	}

}
