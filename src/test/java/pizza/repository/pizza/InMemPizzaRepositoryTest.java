package pizza.repository.pizza;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import pizza.domain.Pizza;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public class InMemPizzaRepositoryTest {
	
	@Test(expected = NoSuchPizzaException.class)
	public void getPizzaByID() throws NoSuchPizzaException {
		InMemPizzaRepository repository = new InMemPizzaRepository();
		repository.getPizzaByID(-1);
	}
	
	@Test
	public void getPizzaByIDNonException() throws Exception {
		PizzaRepository repository = createBeanForPizzaRepository();
		Pizza result = repository.getPizzaByID(1);
		Pizza nonExpected = null;
		assertThat(result, is(not(nonExpected)));
	}

	private PizzaRepository createBeanForPizzaRepository()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		PizzaRepository repository = new InMemPizzaRepository();
		invokeInitMethod(repository);
		return repository;
	}

	private void invokeInitMethod(PizzaRepository repository)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Method method = repository.getClass().getDeclaredMethod("cookPizzas");
		method.setAccessible(true);
		method.invoke(repository);
	}

}
