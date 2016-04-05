package pizza.service.discountservice;

import pizza.domain.Discount;
import pizza.domain.order.Order;

public interface DiscountBuilder {
	
	Discount buildDiscount(Order order);

}
