package pizza.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import pizza.domain.order.Order;

@Entity
@Table(name = "pizza", catalog = "pizza_service_jpa")
public class Pizza {

	private int id;

	private String name;

	private double price;

	@Enumerated(EnumType.STRING)
	private PizzaType type;
	
//	private List<Order> orders = new ArrayList<Order>();

	public Pizza() {}

	public Pizza(int id, String name, double price, PizzaType type) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "pizza_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public PizzaType getType() {
		return type;
	}

	public void setType(PizzaType type) {
		this.type = type;
	}
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}
	
	public static enum PizzaType {
		VEGETARIAN,
		SEA,
		MEAT,
		VEGETABLES
	}
	
}
