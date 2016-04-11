package pizza.service.discountservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizza.domain.Discount;
import pizza.domain.order.Order;
import pizza.service.CardService;
import pizza.service.discountservice.builders.AccumulativeCardDiscountBuilder;
import pizza.service.discountservice.builders.FourthPizzaDiscountBuilder;

@Service(value = "discountProvider")
public class DiscountProvider {

	private List<DiscountBuilder> discountBuilderList;

	@Autowired
	private CardService cardService; 

	public DiscountProvider() {
		super();
		this.discountBuilderList = new ArrayList<DiscountBuilder>();
	}

	public List<Discount> getDiscountList(Order order) {
		List<Discount> discounts = new ArrayList<Discount>();
		for (DiscountBuilder builder : discountBuilderList) {
			Optional<Discount> discount = builder.buildDiscount(order);
			if (discount.isPresent()) {
				discounts.add(discount.get());
			}
		}
		return discounts;
	}

	@PostConstruct
	private void createDiscounts() {
		discountBuilderList = new ArrayList<DiscountBuilder>();
		discountBuilderList.add(new AccumulativeCardDiscountBuilder(cardService));
		discountBuilderList.add(new FourthPizzaDiscountBuilder());
	}

}
