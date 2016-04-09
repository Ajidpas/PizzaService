package pizza.service.discountservice;

import java.util.Optional;

import pizza.domain.Discount;
import pizza.domain.order.Order;

public interface DiscountBuilder {
	
	Optional<Discount> buildDiscount(Order order);

}
