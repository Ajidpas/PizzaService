package pizza.service.discountservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizza.domain.Discount;
import pizza.domain.order.Order;
import pizza.repository.DiscountRepository;
import pizza.service.DiscountService;

@Service(value = "discountService")
public class SimpleDiscountService implements DiscountService {
	
	@Autowired
	private DiscountProvider discountProvider;
	
	@Autowired
	private DiscountRepository discountRepository;

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
