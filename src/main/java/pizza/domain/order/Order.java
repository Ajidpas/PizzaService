package pizza.domain.order;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pizza.domain.Pizza;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.domain.order.status.EnumStatusState;

@Component("order")
@Scope("prototype")
//@Domain
@Entity
@Table(name = "order", catalog = "pizza_service_jpa")
public class Order {
	
	private int id;
	
	private Calendar date;
	
	private Customer customer;
	
	private Map<Pizza, Integer> pizzas;

	private EnumStatusState status;
	
	private Address address;

	private double totalPrice;
	
//	private List<Discount> discounts;
	
	public Order() {}
	
	public Order(Customer customer, Map<Pizza, Integer> pizzas) {
		this.customer = customer;
		this.pizzas = pizzas;
	}
	
	@Temporal(TemporalType.DATE)
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public double getTotalPrice() {
		double totalPrice = 0;
		if (pizzas == null) {
			return this.totalPrice = 0;
		}
		Set<Pizza> pizzaSet = pizzas.keySet();
		for (Pizza pizza : pizzaSet) {
			double pizzaPrice = pizza.getPrice();
			int pizzaNumber = pizzas.get(pizza);
			totalPrice += pizzaPrice * pizzaNumber;
		}
		return this.totalPrice = totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@ManyToOne
	@JoinColumn(name = "address")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@ElementCollection
	@CollectionTable(name = "order_pizza"/*, joinColumns = @JoinColumn(name = "order_id")*/)
	@MapKeyJoinColumn(name = "pizza_id")
	@Column(name = "pizza_number")
	public Map<Pizza, Integer> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Map<Pizza, Integer> pizzas) {
		this.pizzas = pizzas;
	}
	
	@Transient
	public void addPizza(Pizza pizza, Integer pizzaNumber) {
		if (pizzas == null) {
			pizzas = new HashMap<Pizza, Integer>();
		}
		if (pizza != null) {
			if (pizzas.containsKey(pizza)) {
				int newPizzaNumber = pizzas.get(pizza) + pizzaNumber;
				pizzas.put(pizza, newPizzaNumber);
			} else {
				pizzas.put(pizza, pizzaNumber);
			}
		}
	}

	@Transient
	public boolean deletePizza(Pizza pizza, Integer pizzaNumber) {
		if (pizzas.size() >= 1 && pizzas.containsKey(pizza) && pizzaNumber > 0) {
			return deletePizzaFromMap(pizza, pizzaNumber);
		}
		return false;
	}

	private boolean deletePizzaFromMap(Pizza pizza, Integer pizzaNumber) {
		int newPizzaNumber = pizzas.get(pizza) - pizzaNumber;
		if (newPizzaNumber == 0) {
			pizzas.remove(pizza);
			return true;
		}
		if (newPizzaNumber > 0) {
			pizzas.put(pizza, newPizzaNumber);
			return true;
		} 
		return false;
	}

	@Enumerated(EnumType.STRING)
	public EnumStatusState getStatus() {
		return status;
	}

	public void setStatus(EnumStatusState status) {
		this.status = status;
	}
	
//	public boolean addDiscount(Discount discount) {
//		return discounts.add(discount);
//	}
//
//	public void cleanDiscounts() {
//		discounts = new ArrayList<Discount>();
//	}

//	public boolean isStatus() {
//		return status != null;
//	}

//	public List<Discount> getDiscounts() {
//		return discounts;
//	}
//	
//	public void setDiscounts(List<Discount> discounts) {
//		this.discounts = discounts;
//	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", pizzaList=" + pizzas + "]";    //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
	}

}
