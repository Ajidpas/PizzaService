package pizza.service.orderservice;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pizza.domain.Discount;
import pizza.domain.Pizza;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.domain.order.StatusState;
import pizza.domain.order.status.EnumStatusState;
import pizza.domain.order.status.NullOrderStatusException;
import pizza.repository.OrderRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.DiscountService;
import pizza.service.OrderService;
import pizza.service.PizzaService;
import pizza.service.orderservice.exceptions.EmptyOrderException;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

@Service
@Transactional
public class SimpleOrderService implements OrderService {

	private static final int MIN_NUMBER_OF_PIZZAS = 1;

	private static final int MAX_NUMBER_OF_PIZZAS = 10;
	
	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private DiscountService discountService;

	@Override
	public Order placeNewOrder(Customer customer, Address address, Integer... pizzasID)
			throws NotSupportedPizzasNumberException, NoSuchPizzaException,
			WrongStatusException {
		List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
		checkPizzasNumber(pizzas.size());
		Order newOrder = createOrder(customer, address, pizzas);
		setOrderStatus(newOrder);
		newOrder.setDate(new Date(new java.util.Date().getTime()));
		orderRepository.saveOrder(newOrder);
		return newOrder;
	}
	
	protected List<Pizza> pizzasByArrOfId(Integer... pizzasID) throws NoSuchPizzaException {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		for (Integer id : pizzasID) {
			pizzas.add(pizzaService.getPizza(id));
		}
		return pizzas;
	}
	
	private void checkPizzasNumber(Integer pizzasnumber) throws NotSupportedPizzasNumberException {
		if (pizzasnumber < MIN_NUMBER_OF_PIZZAS || pizzasnumber > MAX_NUMBER_OF_PIZZAS) {
			throw new NotSupportedPizzasNumberException();
		}
	}
	
	protected Order createOrder(Customer customer, Address address, List<Pizza> pizzas) {
		Order order = createOrder();
		order.setCustomer(customer);
		order.setAddress(address);
		for (Pizza pizza : pizzas) {
			order.addPizza(pizza, 1);
		}
		return order;
	}
	
	@Lookup
	protected Order createOrder() {
		return null;
	}

	private void setOrderStatus(Order newOrder) {
		StatusState status = EnumStatusState.NEW;
		try {
			status.doAction(newOrder);
		} catch (NullOrderStatusException e) {
			e.printStackTrace();
		}
	}

	private void checkOrderStatus(Order order, StatusState expectedStatus) 
			throws WrongStatusException {
		if (order.getStatus() != expectedStatus) {
			throw new WrongStatusException();
		}
	}

	public double getOrderPrice(Order order) {
		return order.getTotalPrice();
	}

	public double getOrderDiscount(Order order) {
		return discountService.getDiscount(order);
	}
	
	public List<Discount> getOrderDiscounts(Order order) {
		return discountService.getOrderDiscounts(order);
	}

	@Override
	public StatusState cancelOrder(Order order) throws WrongStatusException,
			NullOrderStatusException {
		StatusState status = EnumStatusState.CANCELED;
		StatusState resultStatus = status.doAction(order);
		if (resultStatus != EnumStatusState.CANCELED) {
			throw new WrongStatusException();
		}
		return resultStatus;
	}

	@Override
	public StatusState confirmOrderByAdmin(Order order)
			throws WrongStatusException, NullOrderStatusException {
		StatusState status = EnumStatusState.DONE;
		StatusState resultStatus = status.doAction(order);
		if (resultStatus != EnumStatusState.DONE) {
			throw new WrongStatusException();
		}
		return resultStatus;
	}

	@Override
	public StatusState confirmOrderByUser(Order order)
			throws WrongStatusException, EmptyOrderException, NullOrderStatusException {
		if (countPizzasInMap(order.getPizzas()) == 0) {
			throw new EmptyOrderException();
		}
		StatusState status = EnumStatusState.IN_PROGRESS;
		StatusState resultStatus = status.doAction(order);
		if (resultStatus != EnumStatusState.IN_PROGRESS) {
			throw new WrongStatusException();
		}
		return resultStatus;
	}
	
	private int countPizzasInMap(Map<Pizza, Integer> pizzas) {
		int count = 0;
		Set<Pizza> pizzaSet = pizzas.keySet();
		for (Pizza pizza : pizzaSet) {
			count += pizzas.get(pizza);
		}
		return count;
	}

	@Override
	public List<Pizza> deletePizzasFromOrder(int orderId, Integer... pizzasId) 
			throws NoSuchPizzaException, WrongStatusException {
		Order orderWithPizzas = orderRepository.getOrderWithPizzas(orderId);
		checkOrderStatus(orderWithPizzas, EnumStatusState.NEW);
		List<Pizza> deletedPizzas = new ArrayList<Pizza>();
		for (Pizza pizza : pizzasByArrOfId(pizzasId)) {
			if (orderWithPizzas.deletePizza(pizza, 1)) {
				deletedPizzas.add(pizza);
			}
		}
		orderRepository.updateOrderPizzas(orderWithPizzas);
		return deletedPizzas;
	}

	@Override
	public Map<Pizza, Integer> addPizzasIntoOrder(Order order, Integer... pizzasId)
			throws WrongStatusException, NotSupportedPizzasNumberException, NoSuchPizzaException {
		checkOrderStatus(order, EnumStatusState.NEW);
		int orderPizzas = countPizzasInMap(order.getPizzas());
		int allPizzas = pizzasId.length + orderPizzas;
		checkPizzasNumber(allPizzas);
		for (Pizza pizza : pizzasByArrOfId(pizzasId)) {
			order.addPizza(pizza, 1);
		}
		orderRepository.updateOrder(order);
		return order.getPizzas();
	}

	@Override
	public void addPizzasIntoOrder(Integer orderId, Integer ... pizzasId) 
			throws NoSuchPizzaException, WrongStatusException, NotSupportedPizzasNumberException {
		Order orderWithPizzas = orderRepository.getOrderWithPizzas(orderId);
		checkOrderStatus(orderWithPizzas, EnumStatusState.NEW);
		int orderPizzas = countPizzasInMap(orderWithPizzas.getPizzas());
		int allPizzas = pizzasId.length + orderPizzas;
		checkPizzasNumber(allPizzas);
		for (Pizza pizza : pizzasByArrOfId(pizzasId)) {
			orderWithPizzas.addPizza(pizza, 1);
		}
		orderRepository.updateOrderPizzas(orderWithPizzas);
	}
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}
	
	@Override
	public Order getOrder(int id) {
		return orderRepository.getOrder(id);
	}
	
	@Override
	public Map<Pizza, Integer> getOrderPizzas(int orderId) {
		Order order = orderRepository.getOrderWithPizzas(orderId);
		return order.getPizzas();
	}
	
}
