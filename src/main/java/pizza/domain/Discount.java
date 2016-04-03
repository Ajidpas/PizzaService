package pizza.domain;

import pizza.domain.order.Order;

public interface Discount {
	
	double getDiscount(Order order);

}
