package pizza.domain.customer;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.AccumulativeCard;

public class Customer {
	
	private long id;
	
	private String name;

	private List<Address> addresses;
	
	private AccumulativeCard accumulativeCard;
	
	private Customer(long id, String name) {
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
	
	public boolean isAccumulativeCard() {
		return accumulativeCard != null;
	}

	public AccumulativeCard getAccumulativeCard() {
		return accumulativeCard;
	}

	public void setAccumulativeCard(AccumulativeCard accumulativeCard) {
		this.accumulativeCard = accumulativeCard;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
