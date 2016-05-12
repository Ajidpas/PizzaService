package pizza.domain;

import javax.persistence.*;

import pizza.domain.customer.Customer;

@Entity
@Table(name = "accumulative_card"/*, catalog = "pizza_service_jpa"*/)
public class AccumulativeCard {
	
	private int id;

	private double money;
	
	private Customer customer;
	
	public AccumulativeCard(int id) {
		super();
		this.id = id;
	}
	
	public AccumulativeCard() {}

	public AccumulativeCard(double money, Customer customer) {
		super();
		this.money = money;
		this.customer = customer;
	}

	public AccumulativeCard(int id, Customer customer) {
		this(id);
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "money")
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
