package pizza.service.orderservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pizza.domain.Discount;
import pizza.domain.Pizza;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.domain.order.StatusState;
import pizza.domain.order.status.EnumStatusState;
import pizza.domain.order.status.NullOrderStatusException;
import pizza.repository.OrderRepository;
import pizza.repository.PizzaRepository;
import pizza.repository.order.InMemOrderRepository;
import pizza.repository.pizza.InMemPizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.DiscountService;
import pizza.service.OrderService;
import pizza.service.discountservice.SimpleDiscountService;
import pizza.service.orderservice.exceptions.EmptyOrderException;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public class SimpleOrderService implements OrderService {
	
	private static final int MIN_NUMBER_OF_PIZZAS = 1;
	
	private static final int MAX_NUMBER_OF_PIZZAS = 10;

	private PizzaRepository pizzaRepository;

	private OrderRepository orderRepository;

	private DiscountService discountService;

	public SimpleOrderService(PizzaRepository pizzaRepository, 
			OrderRepository orderRepository, DiscountService discountService) {
		this.pizzaRepository = pizzaRepository;
		this.orderRepository = orderRepository;
		this.discountService = discountService;
	}

	public Order placeNewOrder(Customer customer, Integer ... pizzasID) 
			throws NotSupportedPizzasNumberException, NoSuchPizzaException {
		checkPizzasNumber(pizzasID.length);
		List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
		Order newOrder = createOrder(customer, pizzas);
		setOrderStatus(newOrder);
		orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
		return newOrder;
	}

	private void setOrderStatus(Order newOrder) {
		StatusState status = EnumStatusState.NEW;
		try {
			status.doAction(newOrder);
		} catch (NullOrderStatusException e) {
			e.printStackTrace();
		}
	}

	private void checkPizzasNumber(Integer pizzasnumber) throws NotSupportedPizzasNumberException {
		if (pizzasnumber <= MIN_NUMBER_OF_PIZZAS 
				|| pizzasnumber >= MAX_NUMBER_OF_PIZZAS) {
			throw new NotSupportedPizzasNumberException();
		}
	}

	private List<Pizza> pizzasByArrOfId(Integer... pizzasID) throws NoSuchPizzaException {
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

	public boolean addPizzasIntoOrder(Order order, Integer ... pizzasID) 
			throws WrongStatusException, NotSupportedPizzasNumberException, NoSuchPizzaException {
		checkOrderStatus(order, EnumStatusState.NEW);
		int orderPizzas = order.getPizzaList().size();
		int allPizzas = pizzasID.length + orderPizzas;
		checkPizzasNumber(allPizzas);
		List<Pizza> pizzas;
		pizzas = pizzasByArrOfId(pizzasID);
		for (Pizza pizza : pizzas) {
			order.addPizza(pizza);
		}
		return true;
	}

	private void checkOrderStatus(Order order, StatusState status) throws WrongStatusException {
		if (order.getStatus() == status) {
			throw new WrongStatusException();
		}
	}

	public boolean deletePizzasFromOrder(Order order, Integer ... pizzasID) throws NoSuchPizzaException {
		int orderPizzas = order.getPizzaList().size();
		if (orderPizzas - pizzasID.length >= 1) {
			List<Pizza> pizzas;
			pizzas = pizzasByArrOfId(pizzasID);
			for (Pizza pizza : pizzas) {
				order.deletePizza(pizza.getId());	 
			}
			return true;
		}
		return false;
	}

	public double getOrderPrice(Order order) {
		return order.getOrderPrice();
	}

	public double getOrderDiscount(Order order) {
		return discountService.getDiscount(order);
	}

	public List<Discount> getOrderDiscounts(Order order) {
		return discountService.getOrderDiscounts(order);
	}

	public StatusState confirmOrderByUser(Order order) 
			throws WrongStatusException, EmptyOrderException, 
			NullOrderStatusException {
		if (order.getPizzaList().size() == 0) {
			throw new EmptyOrderException();
		}
		StatusState status = EnumStatusState.IN_PROGRESS;
		StatusState resultStatus = status.doAction(order);
		if (resultStatus != EnumStatusState.IN_PROGRESS) {
			throw new WrongStatusException();
		}
		return resultStatus;
	}

	public StatusState confirmOrderByAdmin(Order order) throws WrongStatusException, 
			NullOrderStatusException {
		StatusState status = EnumStatusState.DONE;
		StatusState resultStatus = status.doAction(order);
		if (resultStatus != EnumStatusState.DONE) {
			throw new WrongStatusException();
		}
		return resultStatus;
	}

	public StatusState cancelOrder(Order order) throws WrongStatusException, 
			NullOrderStatusException {
		StatusState status = EnumStatusState.CANCELED;
		StatusState resultStatus = status.doAction(order);
		if (resultStatus != EnumStatusState.CANCELED) {
			throw new WrongStatusException();
		}
		return resultStatus;
	}

}
