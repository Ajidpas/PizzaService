package pizza.domain.order.status;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import pizza.domain.Pizza;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.domain.order.StatusState;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.simple.SimpleOrderService;
import pizza.service.simple.exceptions.NotSupportedPizzasNumberException;
import pizza.service.simple.exceptions.WrongStatusException;

public class EnumStatusStateTest {
	
	private SimpleOrderService service = new SimpleOrderService();
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	StatusState status;
	
	@Test
	public void testDoAction() throws NotSupportedPizzasNumberException, 
			NoSuchPizzaException, WrongStatusException, NullOrderStatusException {
		Order order = new Order(customer, new ArrayList<Pizza>());
		
		// check that order has no status
		boolean expectedFalse = false;
		boolean resultFalse = order.isStatus();
		assertEquals(expectedFalse, resultFalse);
		
		// try to set new status
		setAndCheckStatus(order, EnumStatusState.NEW, EnumStatusState.NEW);
		
		// try to set DONE status right after NEW that is wrong
		setAndCheckStatus(order, EnumStatusState.DONE, EnumStatusState.NEW);
		
		// try to set CANCELED status right after NEW that is wrong
		setAndCheckStatus(order, EnumStatusState.CANCELED, EnumStatusState.NEW);
		
		// try to set IN_PROGRESS status right after NEW that is right
		setAndCheckStatus(order, EnumStatusState.IN_PROGRESS, EnumStatusState.IN_PROGRESS);
		
		// try to set NEW status right after IN_PROGRESS that is wrong
		setAndCheckStatus(order, EnumStatusState.NEW, EnumStatusState.IN_PROGRESS);
		
		// try to set DONE status right after IN_PROGRESS that is right
		setAndCheckStatus(order, EnumStatusState.DONE, EnumStatusState.DONE);
		
		// try to set any status right after DONE that is WRONG
		setAndCheckStatus(order, EnumStatusState.NEW, EnumStatusState.DONE);
		setAndCheckStatus(order, EnumStatusState.IN_PROGRESS, EnumStatusState.DONE);
		setAndCheckStatus(order, EnumStatusState.CANCELED, EnumStatusState.DONE);
	}
	
	private void setAndCheckStatus(Order order, StatusState settingStatus, StatusState expectedStatus) 
			throws NullOrderStatusException {
		status = settingStatus;
		StatusState expected = expectedStatus;
		StatusState result = status.doAction(order);
		assertEquals(expected, result);
	}
	
	@Test(expected = NullOrderStatusException.class)
	public void testDoActionSetInProgressStatusWhenStatusIsNull() 
			throws NullOrderStatusException {
		Order order = new Order(customer, new ArrayList<Pizza>());
		
		// try to set IN_PROGRESS status that is wrong
		status = EnumStatusState.IN_PROGRESS;
		status.doAction(order); // NullOrderStatusException will occurs on this line
	}
	
	@Test(expected = NullOrderStatusException.class)
	public void testDoActionSetDoneStatusWhenStatusIsNull() 
			throws NullOrderStatusException {
		Order order = new Order(customer, new ArrayList<Pizza>());
		
		// try to set IN_PROGRESS status that is wrong
		status = EnumStatusState.DONE;
		status.doAction(order); // NullOrderStatusException will occurs on this line
	}
	
	@Test(expected = NullOrderStatusException.class)
	public void testDoActionSetCanceledStatusWhenStatusIsNull() 
			throws NullOrderStatusException {
		Order order = new Order(customer, new ArrayList<Pizza>());
		
		// try to set IN_PROGRESS status that is wrong
		status = EnumStatusState.CANCELED;
		status.doAction(order); // NullOrderStatusException will occurs on this line
	}
	
	@Test
	public void testDoActionSetCanceledStatus() throws NullOrderStatusException {
		Order order = new Order(customer, new ArrayList<Pizza>());
		
		// let's set order into IN_PROGRESS status
		setAndCheckStatus(order, EnumStatusState.NEW, EnumStatusState.NEW);
		setAndCheckStatus(order, EnumStatusState.IN_PROGRESS, EnumStatusState.IN_PROGRESS);
		
		// now let's set canceled status and check it
		setAndCheckStatus(order, EnumStatusState.CANCELED, EnumStatusState.CANCELED);
	}

}
