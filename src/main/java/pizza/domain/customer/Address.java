package pizza.domain.customer;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pizza.domain.order.Order;

@Entity
@Table(name = "address", catalog = "pizza_service_jpa")
public class Address {
	
	private int id;

	private String city;
	
	private String street;
	
	private String house;
	
	private String flat;
	
	private List<Customer> customers;
	
	private List<Order> orders;
	
	public Address() {}

	public Address(String city, String street, String house, String flat) {
		this.city = city;
		this.street = street;
		this.house = house;
		this.flat = flat;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	@ManyToMany(mappedBy = "addresses")
	public List<Customer> getCustomers() {
		return customers;
	}

	@OneToMany(targetEntity = Order.class, mappedBy = "address")
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

}
