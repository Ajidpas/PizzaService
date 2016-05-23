package pizza.web;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.PizzaService;

@ControllerAdvice
public class PizzaControllerAdvice {
	
	@Autowired
	private PizzaService pizzaService;
	
	@ModelAttribute("pizza")
	public Pizza findPizza(
			@RequestParam(value = "pizzaId", required = false) Pizza pizza) 
					throws NoSuchPizzaException {
//		Pizza pizza = null;
//		if (pizzaId != null) {
//			pizza = pizzaService.getPizza(pizzaId);
//		}
		
		System.out.println("Find" + pizza);
		return pizza;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Pizza.class, new PropertyEditorSupport(){
			
			@Override
			public void setAsText(String pizzaId) throws IllegalArgumentException {
				Pizza pizza = null;
				if ((pizzaId == null) || pizzaId.isEmpty()) {
					pizza = new Pizza();
				} else {
					try {
						pizza = pizzaService.getPizza(Integer.valueOf(pizzaId));
					} catch (NoSuchPizzaException e) {
						System.out.println("This exception we cannot throw forward, so try-catch-exception");
					}
				}
				setValue(pizza);
			}
			
		});
		
	}

}
