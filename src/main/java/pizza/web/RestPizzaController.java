package pizza.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.PizzaService;

@RestController
public class RestPizzaController {

	@Autowired
	private PizzaService pizzaService;

	@Secured("ROLE_USER")
	@RequestMapping(value = "/pizza/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<Pizza> find(@PathVariable("id") Integer id) {
		Pizza pizza;
		try {
			pizza = pizzaService.getPizza(id);
		} catch (NoSuchPizzaException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// if (pizza == null) {
		// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		// }
		return new ResponseEntity<>(pizza, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/pizza", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.FOUND)
	public List<Pizza> view() {
		return pizzaService.getAllPizzas();
	}

	// @RequestMapping(value = "/pizza", method = RequestMethod.POST, consumes =
	// "application/json")
	// public ResponseEntity<Pizza> add(@RequestBody Pizza pizza) {
	// Pizza savePizza = pizzaService.insertPizza(pizza);
	//
	// return new ResponseEntity<>(savePizza, HttpStatus.CREATED);
	// }

	@Secured("ROLE_USER")
	@RequestMapping(value = "/pizza", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> add(@RequestBody Pizza pizza, UriComponentsBuilder builder) {
		Pizza savePizza = pizzaService.insertPizza(pizza);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/pizza/{id}").buildAndExpand(savePizza.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/token")
	public CsrfToken csrf(CsrfToken token) {
		return token;
	}

}
