package pizza.domain.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pizza.domain.Pizza;
import pizza.domain.customer.Customer;
import pizza.domain.order.status.EnumStatusState;

@Component("order")
@Scope("prototype")
//@Domain
@Entity
@Table(name = "order", catalog = "pizza_service_jpa")
public class Order {
	
	private int id;
	
	private Customer customer;
	
	private List<Pizza> pizzaList;
	
	private EnumStatusState status;
	
//	private List<Discount> discounts;
	
	public Order() {}
	
	public Order(Customer customer, List<Pizza> pizzas) {
		this.customer = customer;
		this.pizzaList = pizzas;
	}

	public void addPizza(Pizza pizza) {
		if (pizza != null) {
			pizzaList.add(pizza);
		}
	}
	
	public boolean deletePizza(int id) {
		if (pizzaList.size() >= 1) {
			for (Pizza pizza : pizzaList) {
				if (pizza.getId() == id) {
					pizzaList.remove(pizza);
					return true;
				}
			}
		}
		return false;
	}

	@Transient
	public double getOrderPrice() {
		double totalPrice = 0;
		for (Pizza pizza : getPizzaList()) {
			totalPrice += pizza.getPrice();
		}
		return totalPrice;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_pizza", 
	joinColumns = @JoinColumn(name = "order_id"), 
	inverseJoinColumns = @JoinColumn(name = "pizza_id")
	)
	public List<Pizza> getPizzaList() {
		return pizzaList;
	}

	public void setPizzaList(List<Pizza> pizzaList) {
		this.pizzaList = pizzaList;
	}

//	public List<Discount> getDiscounts() {
//		return discounts;
//	}

	public void setStatus(EnumStatusState status) {
		this.status = status;
	}

//	public void setDiscounts(List<Discount> discounts) {
//		this.discounts = discounts;
//	}
	
	public EnumStatusState getStatus() {
		return status;
	}

//	@Override
//	public String toString() {
//		return "Order [id=" + id + ", customer=" + customer + ", pizzaList=" + pizzaList + "]";    //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
//	}

}
