package pizza.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pizza.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/create_order", method = RequestMethod.POST)
	public void createOrder(@RequestParam("pizza_number_map") Map pizzaNumberMap) {
		System.out.println(pizzaNumberMap);
	}

}
