package pizza.service.orderservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.domain.order.StatusState;
import pizza.domain.order.status.EnumStatusState;
import pizza.domain.order.status.NullOrderStatusException;
import pizza.repository.OrderRepository;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.DiscountService;
import pizza.service.orderservice.exceptions.EmptyOrderException;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

@RunWith(MockitoJUnitRunner.class)
public class SimpleOrderServiceTest {

	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	private Pizza pizza = new Pizza(EXISTING_PIZZA_ID, "Some pizza 1", 10.0, PizzaType.MEAT);
	
	private static final int NO_PIZZA_WITH_THIS_ID = 100500;
	
	private static final int EXISTING_PIZZA_ID = 5;
	
	private Order order;

	@Mock
	private PizzaRepository pizzaRepository;

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private DiscountService discountService;
	
	@InjectMocks
	private SimpleOrderService service;
	
	@Spy
	private SimpleOrderService serviceSpy = new SimpleOrderService();
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
//		SimpleOrderService serviceObject = new SimpleOrderService();
//		System.out.println(serviceObject.getClass().getFields());
		
//		for (Field field : service.getClass().getDeclaredFields()) {
//			System.out.println("Field = " + field.getName());
//		}
		// create stub for createOrder method in the spy and test placeNewOrder method correctly
//		when(service.createOrder()).thenReturn(new Order(customer, new ArrayList<Pizza>(Arrays.asList(pizza))));
//		System.out.println(service.createOrder());
		
		// set fields by reflection (instead constructor)
//		Field field = serviceObject.getClass().getDeclaredField("pizzaRepository");
//		System.out.println("Name = " + field.getName());
//		field.setAccessible(true);
//		field.set(service, pizzaRepository);
//		field = service.getClass().getDeclaredField("orderRepository");
//		field.setAccessible(true);
//		field.set(service, orderRepository);
//		field = service.getClass().getDeclaredField("discountService");
//		field.setAccessible(true);
//		field.set(service, discountService);
//		
		Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
		pizzas.put(pizza, 1);
		order = new Order(customer, pizzas);
		
		doReturn(new Order(customer, pizzas)).when(serviceSpy).createOrder();
//		doReturn(new ArrayList<Pizza>(Arrays.asList(pizza))).when(serviceSpy).pizzasByArrOfId(anyObject());
		doReturn(new ArrayList<Pizza>(Arrays.asList(pizza))).when(serviceSpy).pizzasByArrOfId(anyInt());
		
		when(pizzaRepository.getPizzaByID(NO_PIZZA_WITH_THIS_ID)).thenThrow(new NoSuchPizzaException());
		when(pizzaRepository.getPizzaByID(EXISTING_PIZZA_ID)).thenReturn(pizza);
		when(orderRepository.saveOrder(any(Order.class))).thenReturn(null);
	}
	
	@Test(expected = NotSupportedPizzasNumberException.class)
	public void testPlaceNewOrderCheckPizzaNumberZero() throws NotSupportedPizzasNumberException, 
			NoSuchPizzaException, WrongStatusException {
		service.placeNewOrder(customer, null);
	}
	
//	@Test(expected = NotSupportedPizzasNumberException.class)
//	public void testPlaceNewOrderCheckPizzaNumberMoreThenTen() throws NotSupportedPizzasNumberException, 
//			NoSuchPizzaException, WrongStatusException {
//		service.placeNewOrder(customer, null, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11); // TODO with object but not with mock
//	}
	
//	@Test(expected = NoSuchPizzaException.class)
//	public void testPlaceNewOrderPizzasByArrOfId() throws NotSupportedPizzasNumberException, NoSuchPizzaException, WrongStatusException {
//		service.placeNewOrder(customer, null, NO_PIZZA_WITH_THIS_ID);
//	}
	
//	@Test
//	public void testPlaceNewOrder() throws NoSuchPizzaException, NotSupportedPizzasNumberException, WrongStatusException {
//		Order order = serviceSpy.placeNewOrder(customer, EXISTING_PIZZA_ID);
//		Customer expectedCustomer = customer;
//		Customer resultCustomer = order.getCustomer();
//		assertEquals(expectedCustomer, resultCustomer);
//		Pizza expectedPizza = pizza;
//		Pizza resultPizza = order.getPizzas().get(0);
//		assertEquals(expectedPizza, resultPizza);
//	}
	
	@Test(expected = WrongStatusException.class)
	public void testAddPizzasIntoOrderWrongStatus() throws NullOrderStatusException, 
	WrongStatusException, NotSupportedPizzasNumberException, NoSuchPizzaException {
		StatusState status = EnumStatusState.NEW;
		status.doAction(order);
		status = EnumStatusState.IN_PROGRESS;
		status.doAction(order);
		service.addPizzasIntoOrder(order, EXISTING_PIZZA_ID);
	}
	
	@Test(expected = NotSupportedPizzasNumberException.class)
	public void testAddPizzasIntoOrderWrongStatusNotSupportedPizzasNumber() throws NullOrderStatusException, 
			WrongStatusException, NotSupportedPizzasNumberException, NoSuchPizzaException {
		Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
		pizzas.put(pizza, 10);
		order = new Order(customer, pizzas);
		StatusState status = EnumStatusState.NEW;
		status.doAction(order);
		service.addPizzasIntoOrder(order, EXISTING_PIZZA_ID);
	}
	
//	@Test(expected = NoSuchPizzaException.class)
//	public void testAddPizzasIntoOrderNoSuchPizza() throws NullOrderStatusException, 
//			WrongStatusException, NotSupportedPizzasNumberException, NoSuchPizzaException {
//		StatusState status = EnumStatusState.NEW;
//		status.doAction(order);
//		service.addPizzasIntoOrder(order, NO_PIZZA_WITH_THIS_ID);
//	}
	
//	@Test
//	public void testAddPizzasIntoOrder() throws NullOrderStatusException, 
//			WrongStatusException, NotSupportedPizzasNumberException, NoSuchPizzaException {
//		StatusState status = EnumStatusState.NEW;
//		status.doAction(order);
//		Map<Pizza, Integer> pizzas = service.addPizzasIntoOrder(order, EXISTING_PIZZA_ID);
//		int expectedSize = 2; // 1 pizza in order and 1 pizza was added
//		int resultSize = pizzas.get(pizza);
//		assertEquals(expectedSize, resultSize);
//	}
	
//	@Test
//	public void testDeletePizzasFromOrder() throws NoSuchPizzaException {
//		int expectedDeletedPizzasSize = 0;
//		int resultDeletedPizzasSize = service.deletePizzasFromOrder(order).size();
//		assertEquals(expectedDeletedPizzasSize, resultDeletedPizzasSize);
//		
//		// add pizza and delete pizza with the same id
//		expectedDeletedPizzasSize = 1;
//		resultDeletedPizzasSize = service.deletePizzasFromOrder(order, EXISTING_PIZZA_ID).size();
//		assertEquals(expectedDeletedPizzasSize, resultDeletedPizzasSize);
//		
//		// add two pizzas and delete only one pizza with such id
//		order.addPizza(pizza, 1);
//		order.addPizza(pizza, 1);
//		expectedDeletedPizzasSize = 1;
//		resultDeletedPizzasSize = service.deletePizzasFromOrder(order, EXISTING_PIZZA_ID).size();
//		assertEquals(expectedDeletedPizzasSize, resultDeletedPizzasSize);
//		
//		// add two pizzas and delete two pizzas with such id
//		order.addPizza(pizza, 1);
//		expectedDeletedPizzasSize = 2;
//		resultDeletedPizzasSize = service.deletePizzasFromOrder(order, EXISTING_PIZZA_ID, EXISTING_PIZZA_ID).size();
//		assertEquals(expectedDeletedPizzasSize, resultDeletedPizzasSize);
//	}
	
	@Test(expected = WrongStatusException.class)
	public void confirmOrderByUserWrongStatus() throws NullOrderStatusException, 
			WrongStatusException, EmptyOrderException {
		StatusState status = EnumStatusState.NEW;
		status.doAction(order);
		status = EnumStatusState.IN_PROGRESS;
		status.doAction(order);
		status = EnumStatusState.DONE;
		status.doAction(order);
		service.confirmOrderByUser(order);
	}
	
	@Test(expected = EmptyOrderException.class)
	public void confirmOrderByUserEmptyOrder() throws NullOrderStatusException, 
			WrongStatusException, EmptyOrderException {
		Order order = new Order(customer, new HashMap<Pizza, Integer>());
		StatusState status = EnumStatusState.NEW;
		status.doAction(order);
		service.confirmOrderByUser(order);
	}
	
	@Test(expected = NullOrderStatusException.class)
	public void confirmOrderByUserNullOrderStatus() throws WrongStatusException, 
			EmptyOrderException, NullOrderStatusException {
		service.confirmOrderByUser(order);
	}
	
	@Test
	public void confirmOrderByUser() throws NullOrderStatusException, 
			WrongStatusException, EmptyOrderException {
		StatusState status = EnumStatusState.NEW;
		status.doAction(order);
		StatusState expected = EnumStatusState.IN_PROGRESS;
		StatusState result = service.confirmOrderByUser(order);
		assertEquals(expected, result);
	}
	
	@Test(expected = WrongStatusException.class)
	public void confirmOrderByAdminWrongStatus() throws NullOrderStatusException, 
			WrongStatusException {
		StatusState status = EnumStatusState.NEW;
		status.doAction(order);
		service.confirmOrderByAdmin(order);
	}
	
	@Test(expected = NullOrderStatusException.class)
	public void confirmOrderByAdminNullOrderStatus() throws WrongStatusException, 
			NullOrderStatusException {
		service.confirmOrderByAdmin(order);
	}
	
	@Test
	public void confirmOrderByAdmin() throws NullOrderStatusException, 
			WrongStatusException {
		StatusState status = EnumStatusState.NEW;
		status.doAction(order);
		status = EnumStatusState.IN_PROGRESS;
		status.doAction(order);
		StatusState expected = EnumStatusState.DONE;
		StatusState result = service.confirmOrderByAdmin(order);
		assertEquals(expected, result);
	}
	
	@Test(expected = WrongStatusException.class)
	public void cancelOrderWrongStatus() throws NullOrderStatusException, 
			WrongStatusException {
		StatusState status = EnumStatusState.NEW;
		status.doAction(order);
		status = EnumStatusState.IN_PROGRESS;
		status.doAction(order);
		status = EnumStatusState.DONE;
		status.doAction(order);
		service.cancelOrder(order);
	}
	
	@Test(expected = NullOrderStatusException.class)
	public void cancelOrderNullOrderStatus() throws WrongStatusException, 
			NullOrderStatusException {
		service.cancelOrder(order);
	}
	
	@Test
	public void cancelOrder() throws NullOrderStatusException, 
			WrongStatusException {
		StatusState status = EnumStatusState.NEW;
		status.doAction(order);
		status = EnumStatusState.IN_PROGRESS;
		status.doAction(order);
		StatusState expected = EnumStatusState.CANCELED;
		StatusState result = service.cancelOrder(order);
		assertEquals(expected, result);
	}

}
