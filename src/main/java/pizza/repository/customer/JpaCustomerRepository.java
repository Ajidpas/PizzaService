package pizza.repository.customer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.repository.CustomerRepository;

@Transactional
@Repository(value = "customerRepository")
public class JpaCustomerRepository implements CustomerRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public JpaCustomerRepository() {}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomers() {
		Query query = em.createQuery("SELECT customers FROM pizza_service_jpa.customer customers", Customer.class);
		return query.getResultList();
	}
	
	@Override
	public Customer saveCustomer(Customer customer) {
		em.persist(customer);
		return customer;
	}

	@Override
	public Customer getCustomer(int id) {
		return em.find(Customer.class, id);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer oldCustomer = em.find(Customer.class, customer.getId());
//		oldCustomer.setAddresses(customer.getAddresses());
		oldCustomer.setName(customer.getName());
		return oldCustomer;
	}

	@Override
	public boolean deleteCustomer(int id) {
		Customer customer = em.find(Customer.class, id);
		if (customer == null) {
			return false;
		}
		em.remove(customer);
		return true;
	}
	
	@Override
	public Customer getCustomerWithAddresses(int customerId) {
		Customer customer = em.find(Customer.class, customerId);
		customer.getAddresses().size();
		return customer;
	}

	@Override
	public void updateAddresses(Customer customerWithAddresses) {
		Integer customerId = customerWithAddresses.getId();
		Customer customer = em.find(Customer.class, customerId);
		customer.setAddresses(customerWithAddresses.getAddresses());
	}
	
}
