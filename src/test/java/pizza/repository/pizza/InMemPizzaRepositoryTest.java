package pizza.repository.pizza;

import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public class InMemPizzaRepositoryTest {
	
	@Test(expected = NoSuchPizzaException.class)
	public void getPizzaByID() throws NoSuchPizzaException {
		InMemPizzaRepository repository = new InMemPizzaRepository();
		repository.getPizzaByID(-1);
	}
	
	@Test
	public void getPizzaByIDNonException() throws NoSuchPizzaException {
		InMemPizzaRepository repository = new InMemPizzaRepository();
		Pizza result = repository.getPizzaByID(1);
		Pizza nonExpected = null;
		assertThat(result, is(not(nonExpected)));
	}

}
