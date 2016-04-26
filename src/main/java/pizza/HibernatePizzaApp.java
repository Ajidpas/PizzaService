package pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import pizza.domain.AccumulativeCard;
import pizza.domain.Pizza;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;

public class HibernatePizzaApp {
	
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Order.class);
		configuration.addAnnotatedClass(Pizza.class);
		configuration.addAnnotatedClass(Customer.class);
		configuration.addAnnotatedClass(Address.class);
		configuration.addAnnotatedClass(AccumulativeCard.class);
		
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setName("Vasya");
		
		Address address = new Address();
		address.setCity("Kiev");
		address.setFlat("5");
		address.setHouse("10");
		address.setStreet("Kudryashova");
		
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		customer.setAddresses(addresses);
		
		Pizza pizza1 = new Pizza();
		pizza1.setName("Pizza from JPA 1");
		pizza1.setPrice(74.15);
		pizza1.setType(Pizza.PizzaType.MEAT);
		Pizza pizza2 = new Pizza();
		pizza2.setName("Pizza from JPA 2");
		pizza2.setPrice(154.21);
		pizza2.setType(Pizza.PizzaType.SEA);
		Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
		pizzas.put(pizza1, 8);
		pizzas.put(pizza2, 12);
		Order order = new Order();
		order.setId(10);
		order.setCustomer(customer);
		order.setPizzas(pizzas);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		
//		customer.setOrders(orders);
		session.save(customer);
		
		session.getTransaction().commit();
	}

}
