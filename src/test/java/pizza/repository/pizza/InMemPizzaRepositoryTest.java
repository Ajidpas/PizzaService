package pizza.repository.pizza;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import pizza.domain.Pizza;
import pizza.repository.Repository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public class InMemPizzaRepositoryTest {
	
	@Test
	public void getPizzaByIDNonException() throws Exception {
		Repository<Pizza> repository = createBeanForPizzaRepository();
		Pizza result = repository.get(1);
		Pizza nonExpected = null;
		assertThat(result, is(not(nonExpected)));
	}

	private Repository<Pizza> createBeanForPizzaRepository()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Repository<Pizza> repository = new InMemPizzaRepository();
		invokeInitMethod(repository);
		return repository;
	}

	private void invokeInitMethod(Repository<Pizza> repository)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Method method = repository.getClass().getDeclaredMethod("cookPizzas");
		method.setAccessible(true);
		method.invoke(repository);
	}

}
