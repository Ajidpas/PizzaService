package pizza.service.discountservice.builders;

import pizza.domain.AccumulativeCard;
import pizza.domain.Discount;
import pizza.domain.customer.Customer;
import pizza.domain.discounts.AccumulativeCardDiscount;
import pizza.domain.order.Order;
import pizza.service.CardService;
import pizza.service.cardservice.SimpleCardService;
import pizza.service.discountservice.DiscountBuilder;

public class AccumulativeCardDiscountBuilder implements DiscountBuilder {

	public Discount buildDiscount(Order order) {
		Customer customer = order.getCustomer();
		CardService cardService = new SimpleCardService();
		AccumulativeCard card = cardService.getCardByCustomer(customer);
		if (card != null) {
			return new AccumulativeCardDiscount(card, order);
		}
		return null;
	}

}
