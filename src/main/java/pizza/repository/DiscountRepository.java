package pizza.repository;

import pizza.domain.Discount;

public interface DiscountRepository {
	
	Discount saveDiscount(Discount discount);

}
