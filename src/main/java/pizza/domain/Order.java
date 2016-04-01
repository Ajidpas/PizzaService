package pizza.domain;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.discounts.FourthPizzaDiscount;

public class Order {
	
	private Long id;
	
	private Customer customer;
	
	private List<Pizza> pizzaList;

	private OrderStatus status;
	
	private Discount discount;
	
	public Order(Customer customer, List<Pizza> pizzas) {
		this.customer = customer;
		pizzaList = new ArrayList();
		pizzaList.addAll(pizzas);
		status = OrderStatus.NEW;
		refreshDiscount();
	}
	
	public void addPizza(Pizza pizza) {
		pizzaList.add(pizza);
		refreshDiscount();
	}
	
	public boolean deletePizza(int id) {
		if (pizzaList.size() >= 1) {
			for (Pizza pizza : pizzaList) {
				if (pizza.getId() == id) {
					pizzaList.remove(pizza);
					refreshDiscount();
					return true;
				}
			}
		}
		return false;
	}

	private void refreshDiscount() {
		discount = new FourthPizzaDiscount(this);
	}
	
	public boolean cancelOrder() {
		if (getStatus().equals(OrderStatus.IN_PROGRSS)) {
			status = OrderStatus.CANCELED;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean nextStatus() {
		switch(getStatus()) {
			case NEW:
				status = OrderStatus.IN_PROGRSS;
				return true;
			case IN_PROGRSS:
				status = OrderStatus.DONE;
				return true;
			default: 
				return false;
		}
	}
	
	private void fillAccumulativeCard() {
		AccumulativeCard card = customer.getAccumulativeCard();
		if (card != null) {
			double price = getTotalPrice();
			card.addMoney(price);
		}
	}
	
	public double getTotalPrice() {
		return getOrderPrice() - getDiscountPrice() - getAccumulativePrice();
	}

	public double getAccumulativePrice() {
		Customer customer = getCustomer();
		AccumulativeCard card = customer.getAccumulativeCard();
		if (card != null) {
			double cardMoney = card.getMoney();
			double orderPrice = getOrderPrice();
			if (cardMoney / 10 < orderPrice * 30 / 100) {
				return cardMoney / 10;
			} else {
				return orderPrice * 30 / 100;
			}
		}
		return 0;
	}

	public double getDiscountPrice() {
		if (discount != null) {
			return discount.getDiscount();
		}
		return 0;
	}

	public double getOrderPrice() {
		double totalPrice = 0;
		for (Pizza pizza : getPizzaList()) {
			totalPrice += pizza.getPrice();
		}
		return totalPrice;
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

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", pizzaList=" + pizzaList + "]";    //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
	}

	public static enum OrderStatus {
		NEW, 
		IN_PROGRSS, 
		CANCELED, 
		DONE
	}

}
