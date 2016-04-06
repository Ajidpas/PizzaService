package pizza.service.discountservice;

import java.util.List;

import pizza.domain.Discount;
import pizza.domain.order.Order;
import pizza.repository.DiscountRepository;
import pizza.repository.discounts.InMemDiscountRepository;
import pizza.service.DiscountService;

public class SimpleDiscountService implements DiscountService {
	
	private DiscountProvider discountProvider;
	
	private DiscountRepository discountRepository;
	
	public SimpleDiscountService(DiscountProvider discountProvider, 
			DiscountRepository discountRepository) {
		this.discountProvider = discountProvider;
		this.discountRepository = discountRepository;
	}

	public double getDiscount(Order order) {
		double entireDiscount = 0;
		for (Discount discount : getOrderDiscounts(order)) {
			entireDiscount += discount.getDiscount();
		}
		return entireDiscount;
	}

	public List<Discount> getOrderDiscounts(Order order) {
		return discountProvider.getDiscountList(order);
	}

	public int saveDiscounts(Order order) {
		List<Discount> discountList = getOrderDiscounts(order);
		for (Discount discount : discountList) {
			discountRepository.saveDiscount(discount);
		}
		return discountList.size();
	}

}
