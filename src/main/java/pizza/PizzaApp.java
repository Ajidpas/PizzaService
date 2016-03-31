package pizza;

import pizza.domain.Customer;
import pizza.domain.Order;
import pizza.service.OrderService;
import pizza.service.simple.SimpleOrderService;
import pizza.view.View;
import pizza.view.console.ConsoleView;

public class PizzaApp {
	
	static View view = new ConsoleView();
	
	public static void main(String[] args) {
		Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10"); //$NON-NLS-1$
		Order order;
		OrderService orderService = new SimpleOrderService();
		order = orderService.placeNewOrder(customer, 1, 2, 3);
		view.printMessage(order.toString());
	}

}
