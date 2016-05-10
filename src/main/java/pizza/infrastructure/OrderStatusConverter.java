package pizza.infrastructure;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import pizza.domain.order.StatusState;
import pizza.domain.order.status.EnumStatusState;

@Converter
public class OrderStatusConverter implements AttributeConverter<StatusState, String> {

	@Override
	public String convertToDatabaseColumn(StatusState statusState) {
		if (statusState == null) {
			return null;
		}
		return ((EnumStatusState) statusState).name();
	}

	@Override
	public StatusState convertToEntityAttribute(String string) {
		if (string == null || string.isEmpty()) {
			return null;
		}
		return EnumStatusState.valueOf(string.toUpperCase());
	}

}
