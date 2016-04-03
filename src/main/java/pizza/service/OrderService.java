package pizza.service;

import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.simple.exceptions.NotSupportedPizzasNumberException;
import pizza.service.simple.exceptions.WrongStatusException;

public interface OrderService {

	Order placeNewOrder(Customer customer, Integer... pizzasID) 
			throws NotSupportedPizzasNumberException, 
			NoSuchPizzaException, WrongStatusException;

}