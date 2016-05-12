package pizza.service;

import java.util.List;
import java.util.Map;

import pizza.domain.Pizza;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.domain.order.StatusState;
import pizza.domain.order.status.NullOrderStatusException;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.orderservice.exceptions.EmptyOrderException;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public interface OrderService {

	Order placeNewOrder(Customer customer, Address address, Integer... pizzasID) 
			throws NotSupportedPizzasNumberException, 
			NoSuchPizzaException, WrongStatusException;
	
	Order getOrder(int id);

	Map<Pizza, Integer> addPizzasIntoOrder(int orderId, Integer ... pizzasID) 
			throws WrongStatusException, NotSupportedPizzasNumberException, 
			NoSuchPizzaException;
	
	StatusState confirmOrderByUser(Order order) 
			throws WrongStatusException, EmptyOrderException, 
			NullOrderStatusException;
	
	StatusState confirmOrderByAdmin(Order order) throws WrongStatusException, 
			NullOrderStatusException;
	
	StatusState cancelOrder(Order order) throws WrongStatusException, 
			NullOrderStatusException;

	List<Order> getAllOrders();

	List<Pizza> deletePizzasFromOrder(int orderId, Integer ... pizzasId)
			throws NoSuchPizzaException, WrongStatusException;
	
}