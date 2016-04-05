package pizza.service.discountservice;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.discountprovider.Discount;
import pizza.domain.discountprovider.DiscountProvider;
import pizza.domain.discountprovider.discounts.AccumulativeCardDiscount;
import pizza.domain.discountprovider.discounts.FourthPizzaDiscount;
import pizza.domain.order.Order;
import pizza.service.DiscountService;

public class SimpleDiscountService implements DiscountService {
	
	private List<Discount> discountList;
	
	public SimpleDiscountService() {
		DiscountProvider discountProvider = new DiscountProvider();
		discountList = discountProvider.getDiscountList();
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
