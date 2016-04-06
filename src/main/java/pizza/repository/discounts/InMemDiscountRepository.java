package pizza.repository.discounts;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.Discount;
import pizza.repository.DiscountRepository;

public class InMemDiscountRepository implements DiscountRepository {

	private List<Discount> discounts;

	public Discount saveDiscount(Discount discount) {
		if (discounts == null) {
			discounts = new ArrayList<Discount>();
		}
		discounts.add(discount);
		return discount;
	}
	
	public List<Discount> getAllDiscounts() {
		if (discounts == null) {
			discounts = new ArrayList<Discount>();
		}
		return discounts;
	}

}
