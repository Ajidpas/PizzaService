package pizza.repository.pizza;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.Pizza;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public class InMemPizzaRepository implements PizzaRepository {

	public List<Pizza> allPizzas;

	public InMemPizzaRepository() {
		allPizzas = new ArrayList<Pizza>();
	}

	public Pizza getPizzaByID(int id) throws NoSuchPizzaException {
		for (Pizza pizza : allPizzas) {
			if (pizza.getId() == id) {
				return pizza;
			}
		}
		throw new NoSuchPizzaException();
//		int index = id - 1;
//		if (index >= 0 && index < allPizzas.size()) {
//			return allPizzas.get(id - 1);
//		} else {
//			throw new NoSuchPizzaException();
//		}
	}

	private void cookPizzas() {
		allPizzas.add(new Pizza(1, "BigPizza", 1.0, Pizza.PizzaType.MEAT)); //$NON-NLS-1$
		allPizzas.add(new Pizza(2, "SmallPizza", 10.0, Pizza.PizzaType.VEGETARIAN)); //$NON-NLS-1$
		allPizzas.add(new Pizza(3, "MediumPizza", 20.0, Pizza.PizzaType.SEA)); //$NON-NLS-1$
		allPizzas.add(new Pizza(4, "HugePizza", 40.0, Pizza.PizzaType.VEGETABLES)); //$NON-NLS-1$
	}

}
