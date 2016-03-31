package pizza.service.simple;

import java.util.ArrayList;
import java.util.List;
import pizza.domain.Customer;
import pizza.domain.Order;
import pizza.domain.Pizza;
import pizza.repository.OrderRepository;
import pizza.repository.PizzaRepository;
import pizza.repository.order.InMemOrderRepository;
import pizza.repository.pizza.InMemPizzaRepository;
import pizza.service.OrderService;

public class SimpleOrderService implements OrderService {
	
	private static final int MIN_NUMBER_OF_PIZZAS = 1;
	
	private static final int MAX_NUMBER_OF_PIZZAS = 10;

	private PizzaRepository pizzaRepository = new InMemPizzaRepository();

	private OrderRepository orderRepository = new InMemOrderRepository();

	public Order placeNewOrder(Customer customer, Integer ... pizzasID) {
		if (pizzasID.length >= 1 || pizzasID.length <= 10) {
			List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
	        Order newOrder = createOrder(customer, pizzas);
	        orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
	        return newOrder;
		}
		return null;
	}
	
	public boolean addPizzaIntoOrder(Order order, Integer ... pizzasID) {
		if (order != null) {
			int orderPizzas = order.getPizzaList().size();
			if (pizzasID.length + orderPizzas <= 10) {
				List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
				for (Pizza pizza : pizzas) {
					order.addPizza(pizza);
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean deletePizzaFromOrder(Order order, Integer ... pizzasID) {
		 if (order != null) {
			 int orderPizzas = order.getPizzaList().size();
			 if (orderPizzas - pizzasID.length >= 1) {
				 List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
				 for (Pizza pizza : pizzas) {
					 order.deletePizza(pizza.getId());	 
				 }
				 return true;
			 }
		 }
		 return false;
	}

	private List<Pizza> pizzasByArrOfId(Integer... pizzasID) {
		List<Pizza> pizzas = new ArrayList<Pizza>();
        for (Integer id : pizzasID) { 
                pizzas.add(pizzaRepository.getPizzaByID(id));  // get Pizza from predifined in-memory list
        }
		return pizzas;
	}

	private Order createOrder(Customer customer, List<Pizza> pizzas) {
		Order newOrder = new Order(customer, pizzas); // change to create order
		return newOrder;
	}

}
