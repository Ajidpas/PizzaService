package pizza.service.discountservice;

import java.util.List;

import pizza.domain.Discount;
import pizza.domain.order.Order;
import pizza.repository.DiscountRepository;
import pizza.repository.discounts.InMemDiscountRepository;
import pizza.service.DiscountService;

public class SimpleDiscountService implements DiscountService {
	
	private DiscountProvider discountProvider;
	
	private List<Discount> discountList;
	
	public SimpleDiscountService(Order order) {
		discountProvider = new DiscountProvider(order);
		discountList = discountProvider.getDiscountList();
	}
	
	public double getDiscount() {
		double entireDiscount = 0;
		for (Discount discount : discountList) {
			entireDiscount += discount.getDiscount();
		}
		return entireDiscount;
	}
	
	public List<Discount> getOrderDiscounts() {
		return discountList;
	}

	public int saveDiscounts() {
		DiscountRepository repository = new InMemDiscountRepository();
		for (Discount discount : discountList) {
			repository.saveDiscount(discount);
		}
		return discountList.size();
	}

}
