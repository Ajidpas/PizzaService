package pizza.domain.order;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pizza.domain.Pizza;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.infrastructure.OrderStatusConverter;

@Component("order")
@Scope("prototype")
//@Domain
@Entity
@Table(name = "t_order"/*, catalog = "pizza_service_jpa"*/)
public class Order {
	
	private int id;
	
	private Date date;
	
	private Customer customer;
	
	private Map<Pizza, Integer> pizzas;

	private StatusState status;
	
	private Address address;

	private double totalPrice;
	
	public Order() {}
	
	public Order(Customer customer, Map<Pizza, Integer> pizzas) {
		this.customer = customer;
		this.pizzas = pizzas;
	}
	
//	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public double countPrice() {
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "address")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "order_pizza", joinColumns = @JoinColumn(name = "order_id"))
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

//	@Enumerated(EnumType.STRING)
	@Convert(converter = OrderStatusConverter.class)
	public StatusState getStatus() {
		return status;
	}

	public void setStatus(StatusState status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", pizzaList=" + pizzas + "]";    //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
	}

}
