package pizza.web;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.PizzaService;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Secured("ROLE_USER")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAllPizzas() {
		List<Pizza> pizzas = pizzaService.getAllPizzas();
		
		// security
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("PizzaController.getAllPizzas(): role name: " + auth.getName());
		
		ModelAndView model = new ModelAndView("pizzas/all_pizzas");
		model.addObject("pizzas", pizzas);
		return model;
	}
	
	@RequestMapping("/{pizza_id}")
	public ModelAndView getPizza(@PathVariable("pizza_id") Pizza pizza) { // converter pizza.web.PizzaConverter does all work for me
		ModelAndView model = new ModelAndView("pizzas/pizza");
		model.addObject("pizza", pizza);
		System.out.println(pizza);
		return model;
	}
	
	@RequestMapping(value = "/add_pizza", method = RequestMethod.GET)
	public ModelAndView addPage() {
		ModelAndView model = new ModelAndView();
		return model;
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "/insert_pizza", method = RequestMethod.POST)
	public String insertPizza(@RequestParam("pizza_name") String name, 
			@RequestParam("pizza_type") Pizza.PizzaType type,
			@RequestParam("pizza_price") Double price) {
		Pizza newPizza = new Pizza();
		newPizza.setName(name);
		newPizza.setPrice(price);
		newPizza.setType(type);
		int id = pizzaService.insertPizza(newPizza).getId();
		return "redirect:/pizzas/" + Integer.toString(id);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "/delete")
	public String deletePizza(@RequestParam("pizza_id") Integer pizzaId) {
		pizzaService.deletePizza(pizzaId);
		return "redirect:/pizzas";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editPizza(@RequestParam("pizza_id") Pizza pizza) {
		ModelAndView model = new ModelAndView();
		model.addObject(pizza);
		return model;
	}
	
	@RequestMapping(value = "/update")
	public String updatePizza(@RequestParam("pizza_name") String name, 
			@RequestParam("pizza_id") Integer pizzaId,
			@RequestParam("pizza_type") Pizza.PizzaType type,
			@RequestParam("pizza_price") Double price) {
		Pizza pizza = new Pizza();
		pizza.setName(name);
		pizza.setPrice(price);
		pizza.setType(type);
		pizza.setId(pizzaId);
		Pizza updatedPizza = pizzaService.updatePizza(pizza);
		return "redirect:/pizzas/" + Integer.toString(updatedPizza.getId());
	}
	
//	@RequestMapping(value = "/add_pizza", method = RequestMethod.POST)
//	public String add(@ModelAttribute Pizza pizza) {
//		System.out.println(pizza);
//		pizzaService.insertPizza(pizza);
//		System.out.println("PizzaController.add method");
//		return "/pizza/add_pizza";
//	}
	
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public String edit() {
//		return "newPizza";
//	}
	
//	@ModelAttribute("pizza")
//	public Pizza findPizza(
//			@RequestParam(value = "pizzaId", required = false) Integer pizzaId) 
//					throws NoSuchPizzaException {
//		Pizza pizza = null;
//		if (pizzaId != null) {
//			pizza = pizzaService.getPizza(pizzaId);
//		}
//		System.out.println("Find" + pizza);
//		return pizza;
//	}
	
//	@ModelAttribute("pizza")
	public Pizza findPizza(
			@RequestParam(value = "pizzaId", required = false) Pizza pizza) 
					throws NoSuchPizzaException {
//		Pizza pizza = null;
//		if (pizzaId != null) {
//			pizza = pizzaService.getPizza(pizzaId);
//		}
		
		System.out.println("Find pizza: " + pizza);
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
