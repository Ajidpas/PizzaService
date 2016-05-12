package pizza.repository;

import java.util.List;

public interface Repository<T> {
	
	List<T> getAll();
	
	T insert(T entity);
	
	T get(int entityId);
	
	T update(T entity);
	
	boolean delete(int entityId);

}
