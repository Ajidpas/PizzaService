package pizza.service;

import java.util.List;
import java.util.Map;

import pizza.domain.Pizza;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.domain.order.StatusState;
import pizza.domain.order.status.NullOrderStatusException;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.orderservice.exceptions.EmptyOrderException;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public interface OrderService {

	Order placeNewOrder(Customer customer, Integer... pizzasID) 
			throws NotSupportedPizzasNumberException, 
			NoSuchPizzaException, WrongStatusException;

	Map<Pizza, Integer> addPizzasIntoOrder(Order order, Integer ... pizzasID) 
			throws WrongStatusException, NotSupportedPizzasNumberException, 
			NoSuchPizzaException;
	
	List<Pizza> deletePizzasFromOrder(Order order, Integer ... pizzasID) 
			throws NoSuchPizzaException;
	
	StatusState confirmOrderByUser(Order order) 
			throws WrongStatusException, EmptyOrderException, 
			NullOrderStatusException;
	
	StatusState confirmOrderByAdmin(Order order) throws WrongStatusException, 
			NullOrderStatusException;
	
	StatusState cancelOrder(Order order) throws WrongStatusException, 
			NullOrderStatusException;
}