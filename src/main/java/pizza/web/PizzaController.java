package pizza.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.PizzaService;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@RequestMapping("/pizza")
	public ModelAndView getPizza() {
		ModelAndView model = new ModelAndView("pizza/pizza");
		Pizza pizza = null;
		try {
			pizza = pizzaService.getPizza(1);
		} catch (NoSuchPizzaException e) {
			model.addObject("error", "No pizza with such id!");
			return model;
		}
		model.addObject("pizza", pizza);
		System.out.println(pizza);
		return model;
	}

}
