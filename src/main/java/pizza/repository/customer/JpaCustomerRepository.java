package pizza.repository.customer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pizza.domain.customer.Customer;
import pizza.repository.CustomerRepository;

public class JpaCustomerRepository implements CustomerRepository {
	
	private EntityManager em;
	
	public JpaCustomerRepository(EntityManager em) {
		super();
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomers() {
		Query query = em.createQuery("SELECT customers FROM pizza_service_jpa.customer customers", Customer.class);
		return query.getResultList();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		return customer;
	}

	@Override
	public Customer getCustomer(int id) {
		return em.find(Customer.class, id);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		em.getTransaction().begin();
		Customer oldCustomer = em.find(Customer.class, customer.getId());
		oldCustomer.setAddresses(customer.getAddresses());
		oldCustomer.setName(customer.getName());
		em.getTransaction().commit();
		return oldCustomer;
	}

	@Override
	public void deleteCustomer(int id) {
		Customer customer = em.find(Customer.class, id);
		em.getTransaction().begin();
		em.remove(customer);
		em.getTransaction().commit();
	}

}
