package pizza.service;

import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public interface OrderService {

	Order placeNewOrder(Customer customer, Integer... pizzasID) 
			throws NotSupportedPizzasNumberException, 
			NoSuchPizzaException, WrongStatusException;

}