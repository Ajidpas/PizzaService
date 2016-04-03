package pizza;

import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.OrderService;
import pizza.service.simple.SimpleOrderService;
import pizza.service.simple.exceptions.NotSupportedPizzasNumberException;
import pizza.service.simple.exceptions.WrongStatusException;
import pizza.view.View;
import pizza.view.console.ConsoleView;

public class PizzaApp {
	
	static View view = new ConsoleView();
	
	public static void main(String[] args) {
		Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10"); //$NON-NLS-1$
		Order order;
		OrderService orderService = new SimpleOrderService();
		try {
			order = orderService.placeNewOrder(customer, 1, 2, 3);
			view.printMessage(order.toString());
		} catch (NotSupportedPizzasNumberException e) {
			view.printMessage(e.getMessage());
		} catch (NoSuchPizzaException e) {
			view.printMessage(e.getMessage());
		} catch (WrongStatusException e) {
			view.printMessage(e.getMessage());
		}
	}

}
