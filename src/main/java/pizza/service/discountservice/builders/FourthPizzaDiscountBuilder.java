package pizza.service.discountservice.builders;

import pizza.domain.Discount;
import pizza.domain.discounts.FourthPizzaDiscount;
import pizza.domain.order.Order;
import pizza.service.discountservice.DiscountBuilder;

public class FourthPizzaDiscountBuilder implements DiscountBuilder {

	public Discount buildDiscount(Order order) {
		return new FourthPizzaDiscount(order);
	}

}
