package pizza.infrastructure;

public interface Config {
	
	Class<?> getImplementation(String bean);

}
