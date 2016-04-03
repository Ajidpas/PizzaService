package pizza.domain.order;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.Discount;
import pizza.domain.Pizza;
import pizza.domain.customer.Customer;
import pizza.domain.order.status.EnumStatusState;

public class Order {
	
	private Long id;
	
	private Customer customer;
	
	private List<Pizza> pizzaList;
	
	private EnumStatusState status;
	
	private List<Discount> discounts;
	
	public Order(Customer customer, List<Pizza> pizzas) {
		this.customer = customer;
		this.pizzaList = pizzas;
	}

	public void addPizza(Pizza pizza) {
		pizzaList.add(pizza);
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

	public double getOrderPrice() {
		double totalPrice = 0;
		for (Pizza pizza : getPizzaList()) {
			totalPrice += pizza.getPrice();
		}
		return totalPrice;
	}
	
	public boolean addDiscount(Discount discount) {
		return discounts.add(discount);
	}
	
	public void cleanDiscounts() {
		discounts = new ArrayList<Discount>();
	}
	
	public boolean isStatus() {
		return status != null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Pizza> getPizzaList() {
		return pizzaList;
	}

	public void setPizzaList(List<Pizza> pizzaList) {
		this.pizzaList = pizzaList;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setStatus(EnumStatusState status) {
		this.status = status;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}
	
	public EnumStatusState getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", pizzaList=" + pizzaList + "]";    //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
	}

}
