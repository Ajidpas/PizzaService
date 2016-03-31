package pizza.domain;

public class Pizza {

	private int id;

	private String name;

	private double price;

	private PizzaType type;

	public Pizza(int id, String name, double price, PizzaType type) {
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
	
	public static enum PizzaType {
		VEGETARIAN,
		SEA,
		MEAT,
		VGETABLES
	}
	
}
