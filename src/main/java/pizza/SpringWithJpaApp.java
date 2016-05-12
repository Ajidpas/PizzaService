package pizza;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pizza.domain.*;
import pizza.domain.customer.*;
import pizza.repository.Repository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.*;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public class SpringWithJpaApp {
	
	public static void main(String[] args) throws WrongStatusException {
		ConfigurableApplicationContext repositoryContext = 
				new ClassPathXmlApplicationContext("mySqlRepositoryContext.xml");
		ConfigurableApplicationContext appContext = 
				new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, false);
		appContext.setParent(repositoryContext);
		appContext.refresh();
		
//		processPizza(appContext);
		
//		processCustomer(appContext);
		
//		processCard(appContext);
		
//		processOrder(appContext);
		
//		addPizzaIntoOrder(appContext); 
		
//		deletePizzaFromOrder(appContext);
		
//		getAllPizzas(appContext);
		
		appContext.close();
		repositoryContext.close();
	}

	private static void getAllPizzas(ConfigurableApplicationContext appContext) {
		Repository orderRepository = appContext.getBean(Repository.class);
		orderRepository.getAll();
	}

	private static void deletePizzaFromOrder(
			ConfigurableApplicationContext appContext) throws WrongStatusException {
		OrderService orderService = appContext.getBean(OrderService.class);
		try {
			orderService.deletePizzasFromOrder(2, 1, 2, 2, 2);
		} catch (NoSuchPizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void addPizzaIntoOrder(
			ConfigurableApplicationContext appContext)  {
		OrderService orderService = appContext.getBean(OrderService.class);
//		Order order = orderService.getOrder(2);
		try {
//			orderService.addPizzasIntoOrder(order, 5);
			orderService.addPizzasIntoOrder(2, 5);
		} catch (WrongStatusException | NotSupportedPizzasNumberException
				| NoSuchPizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void processCard(ConfigurableApplicationContext appContext) {
		CustomerService customerService = appContext.getBean(CustomerService.class);
		Customer customer = customerService.getCustomer(1);
		
		CardService cardService = appContext.getBean(CardService.class);		
		AccumulativeCard card = new AccumulativeCard(.0, customer);
		cardService.insertCard(card);
		
		AccumulativeCard newCard = cardService.getCard(1);
		System.out.println(newCard);
		newCard.setMoney(100);
		waitForEnter("Set value but not persisted");
		newCard.setId(1);
		cardService.updateCard(newCard);
		
		waitForEnter("Before deleting");
		cardService.deleteCard(1);
	}

	private static void waitForEnter(String message) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(message);
		scanner.next();
	}
	

	private static void processCustomer(
			ConfigurableApplicationContext appContext) {
		CustomerService customerService = appContext.getBean(CustomerService.class);
		Customer customer1 = new Customer("Yulia");
		Address address1 = new Address("Lviv", "Evreyska", "10", "5");
		customer1.addAddress(address1);
		
		Customer customer2 = new Customer("Dasha");
		Address address2 = new Address("Rivne", "Kovalska", "1", "75");
		customer2.addAddress(address2);
		
		customerService.insertCustomer(customer1);
		customerService.insertCustomer(customer2);
		
		Customer updatedCustomer = new Customer("Updated customer", "Rivne", "Kovalska", "1", "75");
		customerService.updateCustomer(updatedCustomer);
		
		Customer getedCustomer = customerService.getCustomer(1);
		System.out.println(getedCustomer);
		
		waitForEnter("");
		customerService.deleteCustomer(2);
		
	}
	

//	private static void processOrder(ConfigurableApplicationContext appContext) {
//		CustomerService customerService = appContext.getBean(CustomerService.class);
//		Customer customer = customerService.getCustomer(1);
//		List<Address> addresses = customerService.getAddressesByCustomer(1); // with eager fetching only
//		Address address = addresses.get(0);
//		OrderService orderService = appContext.getBean(OrderService.class);
//		try {
//			orderService.placeNewOrder(customer, address, 1, 1, 1, 1, 2, 2, 1, 2);
//		} catch (NotSupportedPizzasNumberException | NoSuchPizzaException
//				| WrongStatusException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	private static void processPizza(ConfigurableApplicationContext appContext) {
		PizzaService pizzaService = appContext.getBean(PizzaService.class);
		Pizza pizza1 = new Pizza("Pizza1", 321, Pizza.PizzaType.SEA);
		Pizza pizza2 = new Pizza("Pizza2", 123, Pizza.PizzaType.MEAT);
		Pizza pizza3 = new Pizza("Pizza3", 987, Pizza.PizzaType.VEGETABLES);
		
		// insert method
		pizzaService.insertPizza(pizza1);
		pizzaService.insertPizza(pizza2);
		pizzaService.insertPizza(pizza3);
		
		// update method
		Pizza updatePizza = new Pizza("Update pizza", .0, Pizza.PizzaType.VEGETABLES);
		updatePizza.setId(1);
		pizzaService.updatePizza(updatePizza);
		
		// get method
		try {
			System.out.println("Updated pizza = " + pizzaService.getPizza(1));
		} catch (NoSuchPizzaException e) {
			System.out.println("There is no pizza with such id");
			e.printStackTrace();
		}
		
		waitForEnter("");
		pizzaService.deletePizza(3);
	}

	private static void insertCustomer(EntityManager em) {
		Customer customer = new Customer("Vasya1", "Kiev1", "Kudriashova1", "5", "10");
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
	}

	private static void insertPizzas(EntityManager em) {
		Pizza pizza1 = new Pizza();
		pizza1.setName("Pizza1");
		pizza1.setPrice(111);
		pizza1.setType(Pizza.PizzaType.MEAT);
		
		Pizza pizza2 = new Pizza("Pizza2", 222, Pizza.PizzaType.SEA);
		Pizza pizza3 = new Pizza("Pizza3", 333, Pizza.PizzaType.VEGETABLES);
		Pizza pizza4 = new Pizza("Pizza4", 444, Pizza.PizzaType.VEGETARIAN);
		
		em.getTransaction().begin();
		em.persist(pizza1);
		em.persist(pizza2);
		em.persist(pizza3);
		em.persist(pizza4);
		em.getTransaction().commit();
	}

}
