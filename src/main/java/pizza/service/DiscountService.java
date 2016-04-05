package pizza.service;

import java.util.List;

import pizza.domain.Discount;
import pizza.domain.order.Order;

public interface DiscountService {
	
	public double getDiscount();
	
	public List<Discount> getOrderDiscounts();

	public int saveDiscounts();

}
