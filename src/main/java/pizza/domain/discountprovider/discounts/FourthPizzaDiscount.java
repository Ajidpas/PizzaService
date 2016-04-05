package pizza.domain.discountprovider.discounts;

import java.util.List;

import pizza.domain.Pizza;
import pizza.domain.discountprovider.Discount;
import pizza.domain.order.Order;

public class FourthPizzaDiscount implements Discount {

	public double getDiscount(Order order) {
		List<Pizza> pizzas = order.getPizzaList();
		if (pizzas.size() >= 4) {
			return getBiggestPricePizza(pizzas) * 30 / 100;
		} else {
			return 0;
		}
	}

	private double getBiggestPricePizza(List<Pizza> pizzas) {
		double biggerPrice = 0;
		for (Pizza pizza : pizzas) {
			double pizzaPrice = pizza.getPrice();
			if (biggerPrice < pizzaPrice) {
				biggerPrice = pizzaPrice;
			}
		}
		return biggerPrice;
	}

}
