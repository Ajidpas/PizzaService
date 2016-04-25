package pizza.domain.discounts;

import java.util.List;

import pizza.domain.Discount;
import pizza.domain.Pizza;
import pizza.domain.order.Order;

public class FourthPizzaDiscount implements Discount {
	
	private Order order;

	public FourthPizzaDiscount(Order order) {
		super();
		this.order = order;
	}

	public double getDiscount() {
		List<Pizza> pizzas = order.getPizzas();
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
