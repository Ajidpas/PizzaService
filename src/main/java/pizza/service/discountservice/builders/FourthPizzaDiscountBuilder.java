package pizza.service.discountservice.builders;

import java.util.Optional;

import pizza.domain.Discount;
import pizza.domain.discounts.FourthPizzaDiscount;
import pizza.domain.order.Order;
import pizza.service.discountservice.DiscountBuilder;

public class FourthPizzaDiscountBuilder implements DiscountBuilder {

	public Optional<Discount> buildDiscount(Order order) {
		return Optional.of(new FourthPizzaDiscount(order));
	}

}
