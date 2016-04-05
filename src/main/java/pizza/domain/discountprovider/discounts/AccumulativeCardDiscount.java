package pizza.domain.discountprovider.discounts;

import pizza.domain.AccumulativeCard;
import pizza.domain.customer.Customer;
import pizza.domain.discountprovider.Discount;
import pizza.domain.order.Order;

public class AccumulativeCardDiscount implements Discount {

	public double getDiscount(Order order) {
		Customer customer = order.getCustomer();
		if (customer.isAccumulativeCard()) {
			AccumulativeCard card = customer.getAccumulativeCard(); // Demetry rule!
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
