package pizza.service;

import pizza.domain.Customer;
import pizza.domain.Order;

public interface OrderService {

	Order placeNewOrder(Customer customer, Integer... pizzasID);

}