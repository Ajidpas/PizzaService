package pizza.repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractRepository<T> implements Repository<T> {
	
	@PersistenceContext
	private EntityManager em;
	
	private Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractRepository() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T insert(T entity) {
		em.persist(entity);
		em.flush();
		return entity;
	}

	@Override
	public T get(int entityId) {
		return em.find(persistentClass, entityId);
	}

	@Override
	public boolean delete(int entityId) {
		T entity = em.find(persistentClass, entityId);
		if (entity == null) {
			return false;
		}
		em.remove(entity);
		em.flush();
		return true;
	}
	
	

}
