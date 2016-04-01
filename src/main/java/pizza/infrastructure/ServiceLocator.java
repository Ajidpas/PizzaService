package pizza.infrastructure;

public class ServiceLocator {
	
	private Config config = new JavaConfig();
	
	private static final ServiceLocator INSTANCE = 
			new ServiceLocator();
	
	private ServiceLocator() {}
	
	public static ServiceLocator getInstance() {
		return INSTANCE;
	}
	
	public Object lookup(String bean) {
		Class<?> clazz = config.getImplementation(bean);
		if (clazz == null) {
			throw new RuntimeException("Bean not found!");
		}
		
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
