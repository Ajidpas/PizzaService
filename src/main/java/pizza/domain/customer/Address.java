package pizza.domain.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address", catalog = "pizza_service_jpa")
public class Address {
	
	private int id;

	private String city;
	
	private String street;
	
	private String house;
	
	private String flat;
	
	private Customer customer;
	
	public Address() {}

	public Address(String city, String street, String house, String flat) {
		this.city = city;
		this.street = street;
		this.house = house;
		this.flat = flat;
	}
	
	@ManyToOne()
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
