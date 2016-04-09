package pizza.service.discountservice;

import static org.junit.Assert.*;
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pizza.domain.Discount;
import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.DiscountRepository;

@RunWith(MockitoJUnitRunner.class)
public class SimpleDiscountServiceTest {
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	private Pizza pizza = new Pizza(1, "Some pizza", 50.0, PizzaType.MEAT);
	
	private Order order = new Order(customer, new ArrayList<>(Arrays.asList(pizza)));
	
	public static final double DISCOUNT_VALUE = 15.0;
	
	@Mock
	private Discount discount;
	
	@Mock
	private DiscountProvider discountProvider;
	
	@Mock
	private DiscountRepository discountRepository;
	
	@InjectMocks
	private SimpleDiscountService service;	
	
	@Before
	public void setUp() {
		when(discountProvider.getDiscountList(any(Order.class)))
		.thenReturn(new ArrayList<Discount>(Arrays.asList(discount,discount,discount)));
		when(discount.getDiscount()).thenReturn(DISCOUNT_VALUE);
	}
	
	@Test
	public void testGetDiscount() {
		double result = service.getDiscount(order);
		double expected = DISCOUNT_VALUE * 3; // regarding stab
		assertEquals(result, expected, 0.0001);
	}
	
	@Test
	public void testSaveDiscounts() {
		int expected = 3; // regarding stab
		int result = service.saveDiscounts(order);
		assertEquals(expected, result);
		verify(discountRepository, times(3)).saveDiscount(any(Discount.class));
	}

}
