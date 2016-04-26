package pizza.domain.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import pizza.domain.AccumulativeCard;
import pizza.domain.order.Order;

@Entity
@Table(name = "customer", catalog = "pizza_service_jpa")
public class Customer /*implements FactoryBean<Customer>*/{
	
	private int id;
	
	private String name;

	private List<Address> addresses;

	public Customer() {}
	
	private Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Customer(int id, String name, Address address) {
		this(id, name);
		addresses = new ArrayList<Address>();
		addresses.add(address);
	}
	
	public Customer(int id, String name, String city, String street, 
			String house, String flat) {
		this(id, name);
		addresses = new ArrayList<Address>();
		addresses.add(new Address(city, street, house, flat));
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "customer_address", 
			joinColumns = @JoinColumn(name = "customer_id"),
			inverseJoinColumns = @JoinColumn(name = "address_id"))
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
//
//	@Override
//	public Customer getObject() throws Exception {
//		return new Customer(1, "Abc");
//	}
//
//	@Override
//	public Class<?> getObjectType() {
//		return Customer.class;
//	}
//
//	@Override
//	public boolean isSingleton() {
//		return false;
//	}

}
