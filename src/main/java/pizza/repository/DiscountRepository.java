package pizza.repository;

import java.util.List;

import pizza.domain.Discount;

public interface DiscountRepository {
	
	Discount saveDiscount(Discount discount);
	
	List<Discount> getAllDiscounts();

}
