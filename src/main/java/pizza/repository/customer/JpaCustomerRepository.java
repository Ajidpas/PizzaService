package pizza.repository.customer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pizza.domain.customer.Customer;
import pizza.repository.AbstractRepository;

@Transactional
@Repository(value = "customerRepository")
public class JpaCustomerRepository extends AbstractRepository<Customer> {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		Query query = em.createQuery("SELECT c FROM Customer c", Customer.class);
		return query.getResultList();
	}

	@Override
	public Customer update(Customer customer) {
		Customer oldCustomer = em.find(Customer.class, customer.getId());
		oldCustomer.setAddresses(customer.getAddresses());
		oldCustomer.setName(customer.getName());
		em.flush();
		return oldCustomer;
	}

}
