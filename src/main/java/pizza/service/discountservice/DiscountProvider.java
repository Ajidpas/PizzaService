package pizza.service.discountservice;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.Discount;
import pizza.domain.order.Order;
import pizza.service.discountservice.builders.AccumulativeCarsDiscountBuilder;
import pizza.service.discountservice.builders.FourthPizzaDiscountBuilder;

public class DiscountProvider {
	
	private List<DiscountBuilder> discountBuilderList;
	
	{
		discountBuilderList = new ArrayList<DiscountBuilder>();
		discountBuilderList.add(new AccumulativeCarsDiscountBuilder());
		discountBuilderList.add(new FourthPizzaDiscountBuilder());
	}
	
	private Order order;

	public DiscountProvider(Order order) {
		super();
		this.order = order;
	}

	public List<Discount> getDiscountList() {
		List<Discount> discounts = new ArrayList<Discount>();
		for (DiscountBuilder builder : discountBuilderList) {
			Discount discount = builder.buildDiscount(order);
			if (discount != null) {
				discounts.add(discount);
			}
		}
		return discounts;
	}

}
