package pizza.service;

import java.util.List;

import pizza.domain.Discount;
import pizza.domain.order.Order;

public interface DiscountService {
	
	public double getDiscount(Order order);
	
	public List<Discount> getOrderDiscounts(Order order);

	public int saveDiscounts(Order order);

}
