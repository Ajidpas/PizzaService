package pizza.domain.discounts;

import pizza.domain.Discount;
import pizza.domain.Order;
import pizza.domain.Pizza;

public class FourthPizzaDiscount implements Discount {
	
	protected Order order;

	public FourthPizzaDiscount(Order order) {
		this.order = order;
	}

	public double getDiscount() {
		if (order.getPizzaList().size() >= 4) {
			return getBiggestPricePizza() * 30 / 100;
		} else {
			return 0;
		}
	}

	private double getBiggestPricePizza() {
		double biggerPrice = 0;
		for (Pizza pizza : order.getPizzaList()) {
			double pizzaPrice = pizza.getPrice();
			if (biggerPrice < pizzaPrice) {
				biggerPrice = pizzaPrice;
			}
		}
		return biggerPrice;
	}

}
