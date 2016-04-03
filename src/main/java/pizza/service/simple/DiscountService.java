package pizza.service.simple;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.Discount;
import pizza.domain.discounts.AccumulativeCardDiscount;
import pizza.domain.discounts.FourthPizzaDiscount;
import pizza.domain.order.Order;

public class DiscountService {
	
	List<Discount> discountList;
	
	{
		discountList = new ArrayList<Discount>();
		discountList.add(new AccumulativeCardDiscount());
		discountList.add(new FourthPizzaDiscount());
	}
	
	public double getDiscount(Order order) {
		List<Discount> orderDiscounts = getOrderDiscounts(order);
		double entireDiscount = 0;
		for (Discount discount : orderDiscounts) {
			entireDiscount += discount.getDiscount(order);
		}
		return entireDiscount;
	}
	
	public List<Discount> getOrderDiscounts(Order order) {
		List<Discount> orderDiscounts = new ArrayList<Discount>();
		for (Discount discount : discountList) {
			if (discount.getDiscount(order) > 0) {
				orderDiscounts.add(discount);
			}
		}
		return orderDiscounts;
	}
	
	public double saveDiscounts(Order order) {
		List<Discount> orderDiscounts = getOrderDiscounts(order);
		double entireDiscount = 0;
		order.cleanDiscounts();
		for (Discount discount : orderDiscounts) {
			entireDiscount += discount.getDiscount(order);
			order.addDiscount(discount);
		}
		return entireDiscount;
	}

}
