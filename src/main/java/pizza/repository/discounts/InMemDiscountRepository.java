package pizza.repository.discounts;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.Discount;
import pizza.repository.DiscountRepository;

public class InMemDiscountRepository implements DiscountRepository {
	
	private List<Discount> discounts;

	public Long saveDiscount(Discount discount) {
		if (discounts == null) {
			discounts = new ArrayList<Discount>();
		}
		discounts.add(discount);
		return null;
	}

}
