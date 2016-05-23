package pizza.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/controller")
public class HelloController {
	
	@RequestMapping(path = "/hello")
	public void hello() {
		System.out.println("hello method");
	}

}
