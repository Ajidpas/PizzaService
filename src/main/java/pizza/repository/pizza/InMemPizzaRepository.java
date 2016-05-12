package pizza.repository.pizza;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import pizza.domain.Pizza;
import pizza.repository.Repository;

//@Repository(value = "pizzaRepository")
public class InMemPizzaRepository implements Repository<Pizza> {

	public List<Pizza> allPizzas;

	public InMemPizzaRepository() {
		allPizzas = new ArrayList<Pizza>();
	}

	public Pizza get(int id) {
		for (Pizza pizza : allPizzas) {
			if (pizza.getId() == id) {
				return pizza;
			}
		}
		return null;
	}

	@PostConstruct
	private void cookPizzas() {
		allPizzas.add(new Pizza(1, "BigPizza", 1.0, Pizza.PizzaType.MEAT)); //$NON-NLS-1$
		allPizzas.add(new Pizza(2, "SmallPizza", 10.0, Pizza.PizzaType.VEGETARIAN)); //$NON-NLS-1$
		allPizzas.add(new Pizza(3, "MediumPizza", 20.0, Pizza.PizzaType.SEA)); //$NON-NLS-1$
		allPizzas.add(new Pizza(4, "HugePizza", 40.0, Pizza.PizzaType.VEGETABLES)); //$NON-NLS-1$
	}

	@Override
	public List<Pizza> getAll() {
		return allPizzas;
	}

	@Override
	public Pizza update(Pizza entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Pizza insert(Pizza entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(int entityId) {
		throw new UnsupportedOperationException();
	}

}
