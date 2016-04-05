package pizza.domain.discounts;

import pizza.domain.AccumulativeCard;
import pizza.domain.Discount;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;

public class AccumulativeCardDiscount implements Discount {
	
	private AccumulativeCard card;
	
	private Order order;

	public AccumulativeCardDiscount(AccumulativeCard card, Order order) {
		super();
		this.card = card;
		this.order = order;
	}

	public double getDiscount() {
		if (card != null) {
			double cardMoney = card.getMoney();
			double orderPrice = order.getOrderPrice();
			if (cardMoney / 10 < orderPrice * 30 / 100) {
				return cardMoney / 10;
			} else {
				return orderPrice * 30 / 100;
			}
		}
		return 0;
	}

}
