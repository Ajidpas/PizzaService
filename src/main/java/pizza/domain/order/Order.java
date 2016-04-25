package pizza.domain.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pizza.domain.*;
import pizza.domain.customer.*;
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
	
	private List<Pizza> pizzas;

	private EnumStatusState status;
	
	private Address address;

	private double totalPrice;
	
//	private List<Discount> discounts;
	
	public Order() {}
	
	public Order(Customer customer, List<Pizza> pizzas) {
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
		for (Pizza pizza : getPizzas()) {
			totalPrice += pizza.getPrice();
		}
		this.totalPrice = totalPrice;
		return this.totalPrice;
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

	@ManyToMany
	@JoinTable(name = "order_pizza",
		joinColumns = @JoinColumn(name = "pizza_id"),
		inverseJoinColumns = @JoinColumn(name = "order_id"))
	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	@Transient
	public void addPizza(Pizza pizza) {
		if (pizzas == null) {
			pizzas = new ArrayList<Pizza>();
		}
		if (pizza != null) {
			pizzas.add(pizza);
		}
	}
	
	@Transient
	public boolean deletePizza(int id) {
		if (pizzas.size() >= 1) {
			for (Pizza pizza : pizzas) {
				if (pizza.getId() == id) {
					pizzas.remove(pizza);
					return true;
				}
			}
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
