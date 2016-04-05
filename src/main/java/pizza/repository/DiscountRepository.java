package pizza.repository;

import pizza.domain.Discount;

public interface DiscountRepository {
	
	Long saveDiscount(Discount discount);

}
