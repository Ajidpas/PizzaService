package pizza.repository.pizza;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import pizza.domain.Pizza;
import pizza.repository.PizzaRepository;

public class InMemPizzaRepository implements PizzaRepository {

	public List<Pizza> allPizzas;

	public InMemPizzaRepository() {
		allPizzas = new ArrayList<Pizza>();
		init();
	}

	public Pizza getPizzaByID(int id) {
		return allPizzas.get(id - 1);
	}

	private void init() {
		allPizzas.add(new Pizza(1, "BigPizza", BigDecimal.ONE, PizzaType.MEAT)); //$NON-NLS-1$
		allPizzas.add(new Pizza(2, "SmallPizza", BigDecimal.TEN, PizzaType.MEAT)); //$NON-NLS-1$
		allPizzas.add(new Pizza(3, "MediumPizza", BigDecimal.valueOf(20.0), PizzaType.MEAT)); //$NON-NLS-1$
	}

}
