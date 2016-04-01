package pizza.infrastructure;

import java.util.HashMap;
import java.util.Map;

import pizza.repository.order.InMemOrderRepository;
import pizza.repository.pizza.InMemPizzaRepository;
import pizza.service.simple.SimpleOrderService;

public class JavaConfig implements Config {
	
	private Map<String, Class<?>> beans = new HashMap<String, Class<?>>();
	
	{
		beans.put("orderRepository", InMemOrderRepository.class);
		beans.put("pizzaRepository", InMemPizzaRepository.class);
		beans.put("orderService", SimpleOrderService.class);
	}

	public Class<?> getImplementation(String bean) {
		return beans.get(bean);
	}

}
