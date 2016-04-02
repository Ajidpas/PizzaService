package pizza.infrastructure;

public interface ApplicationContext {
	
	public Object getBean(String bean)  throws Exception;

}
