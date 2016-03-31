package pizza.repository.pizza;

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
		allPizzas.add(new Pizza(1, "BigPizza", 1.0, Pizza.PizzaType.MEAT)); //$NON-NLS-1$
		allPizzas.add(new Pizza(2, "SmallPizza", 10.0, Pizza.PizzaType.VEGETARIAN)); //$NON-NLS-1$
		allPizzas.add(new Pizza(3, "MediumPizza", 20.0, Pizza.PizzaType.SEA)); //$NON-NLS-1$
		allPizzas.add(new Pizza(4, "HugePizza", 40.0, Pizza.PizzaType.VGETABLES)); //$NON-NLS-1$
	}

}
