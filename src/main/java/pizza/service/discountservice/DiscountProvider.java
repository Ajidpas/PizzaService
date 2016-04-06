package pizza.service.discountservice;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.Discount;
import pizza.domain.order.Order;
import pizza.service.discountservice.builders.AccumulativeCardDiscountBuilder;
import pizza.service.discountservice.builders.FourthPizzaDiscountBuilder;

public class DiscountProvider {

	private List<DiscountBuilder> discountBuilderList;

	public List<Discount> getDiscountList(Order order) {
		List<Discount> discounts = new ArrayList<Discount>();
		for (DiscountBuilder builder : discountBuilderList) {
			Discount discount = builder.buildDiscount(order);
			if (discount != null) {
				discounts.add(discount);
			}
		}
		return discounts;
	}

	private void createDiscounts() {
		discountBuilderList = new ArrayList<DiscountBuilder>();
		discountBuilderList.add(new AccumulativeCardDiscountBuilder());
		discountBuilderList.add(new FourthPizzaDiscountBuilder());
	}

}
