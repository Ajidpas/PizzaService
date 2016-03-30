package pizza.domain;

import java.math.BigDecimal;

import pizza.repository.pizza.PizzaType;

public class Pizza {

	private int id;
	
	private String name;
	
	private BigDecimal price;
	
	private PizzaType type;

	public Pizza(int id, String name, BigDecimal price, PizzaType type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public PizzaType getType() {
		return type;
	}

	public void setType(PizzaType type) {
		this.type = type;
	}
}
