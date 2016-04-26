package pizza.repository.customer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pizza.domain.customer.Customer;
import pizza.repository.CustomerRepository;

@Repository(value = "customerRepository")
public class JpaCustomerRepository implements CustomerRepository {
	
	private EntityManagerFactory emf;
	
	private EntityManager em;
	
	public JpaCustomerRepository() {}
	
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

	@PostConstruct
	private void initEntityManager() {
		emf = Persistence.createEntityManagerFactory("jpa");
		em = emf.createEntityManager();
	}
	
	@PreDestroy
	private void closeEntityManager() {
		em.close();
		emf.close();
	}
	
}
