package pizza.service.discountservice.builders;

import java.util.Optional;

import pizza.domain.AccumulativeCard;
import pizza.domain.Discount;
import pizza.domain.customer.Customer;
import pizza.domain.discounts.AccumulativeCardDiscount;
import pizza.domain.order.Order;
import pizza.service.CardService;
import pizza.service.discountservice.DiscountBuilder;

public class AccumulativeCardDiscountBuilder implements DiscountBuilder {
	
	private CardService cardService;

	public AccumulativeCardDiscountBuilder(CardService cardService) {
		super();
		this.cardService = cardService;
	}

	@Override
	public Optional<Discount> buildDiscount(Order order) {
		Customer customer = order.getCustomer();
		Optional<AccumulativeCard> card = cardService.getCardByCustomer(customer);
		if (card.isPresent()) {
			return Optional.ofNullable(new AccumulativeCardDiscount(card.get(), order));
		}
		return Optional.empty();
	}

}
