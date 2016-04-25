package pizza.domain;

import javax.persistence.*;

@Entity
@Table(name = "pizza", catalog = "pizza_service_jpa")
public class Pizza {

	private int id;

	private String name;

	private double price;

	@Enumerated(EnumType.STRING)
	private PizzaType type;

	public Pizza() {}

	public Pizza(int id, String name, double price, PizzaType type) {
		this(name, price, type);
		this.id = id;
	}
	
	public Pizza(String name, double price, PizzaType type) {
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pizza other = (Pizza) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	public static enum PizzaType {
		VEGETARIAN,
		SEA,
		MEAT,
		VEGETABLES
	}
	
}
