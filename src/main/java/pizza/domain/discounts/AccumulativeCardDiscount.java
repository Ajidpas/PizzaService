package pizza.domain.discounts;

import pizza.domain.Discount;
import pizza.domain.customer.AccumulativeCard;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;

public class AccumulativeCardDiscount implements Discount {

	public double getDiscount(Order order) {
		Customer customer = order.getCustomer();
		AccumulativeCard card = customer.getAccumulativeCard(); // Demetry rule!
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
