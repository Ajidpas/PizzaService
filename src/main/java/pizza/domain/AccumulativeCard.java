package pizza.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pizza.domain.customer.Customer;

@Entity
@Table(name = "accumulative_card", catalog = "pizza_service_jpa")
public class AccumulativeCard {
	
	private long id;

	private double money;
	
	private Customer customer;
	
	public AccumulativeCard(long id) {
		this.id = id;
	}
	
	public AccumulativeCard() {}

	public AccumulativeCard(long id, Customer customer) {
		super();
		this.id = id;
		this.customer = customer;
	}

	@OneToOne
	@JoinColumn(name = "customer_id", unique = true)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	public void addMoney(double money) {
		this.money += money;
	}

}
