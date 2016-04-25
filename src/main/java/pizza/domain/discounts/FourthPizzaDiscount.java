package pizza.domain.discounts;

import java.util.Collection;
import java.util.Set;

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
		int pizzaNumber = 0;
		Collection<Integer> pizzaNumbers = order.getPizzas().values();
		for (int number : pizzaNumbers) {
			pizzaNumber += number;
		}
		if (pizzaNumber >= 4) {
			return getBiggestPricePizza(order.getPizzas().keySet()) * 30 / 100;
		} else {
			return 0;
		}
	}

	private double getBiggestPricePizza(Set<Pizza> pizzas) {
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
