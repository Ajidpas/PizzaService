package pizza.domain.discountprovider;

import pizza.domain.order.Order;

public interface Discount {
	
	double getDiscount(Order order);

}
