package pizza.service.discountservice;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

@RunWith(MockitoJUnitRunner.class)
public class DiscountProviderTest {
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	private Pizza pizza = new Pizza(1, "Some pizza 1", 10.0, PizzaType.MEAT);
	
	@Mock
	private Discount discount;
	
	@Mock
	private DiscountBuilder builder;
	
	@InjectMocks
	private DiscountProvider provider;
	
	@Before
	public void setUp() {
		when(builder.buildDiscount(any(Order.class))).thenReturn(Optional.of(discount));
	}
	
	@Test
	public void testGetDiscountList() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field discountBuilders = DiscountProvider.class.getDeclaredField("discountBuilderList");
		discountBuilders.setAccessible(true);
		
		// set list with 3 discounts as value for this field
		List<DiscountBuilder> discountBuilderList = new ArrayList<DiscountBuilder>(Arrays.asList(builder, builder, builder));
		discountBuilders.set(provider, discountBuilderList);
		provider.getDiscountList(new Order(customer,new ArrayList<Pizza>(Arrays.asList(pizza))));
		int expected = discountBuilderList.size();
		verify(builder, times(expected)).buildDiscount(any(Order.class));
	}

}
