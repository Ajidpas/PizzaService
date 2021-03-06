package pizza.infrastructure;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class JavaConfigApplicationContext implements ApplicationContext {
	
	private Config config = new JavaConfig();
	
	private final Map<String,Object> context = new HashMap<String, Object>(); // cache
	
	public Object getBean(String beanName) throws Exception {
		if (context.containsKey(beanName)) {
			return context.get(beanName);
		}
		
		Class<?> clazz = config.getImplementation(beanName);
		if (clazz == null) {
			throw new RuntimeException("Bean not found!");
		}
		
		Object bean = null;
		
		Constructor<?> constructor = clazz.getConstructors()[0];
		Class<?>[] paramTypes = constructor.getParameterTypes();
		if (paramTypes.length == 0) {
			bean = clazz.newInstance();
		} else {
			Object[] paramBeans = new Object[paramTypes.length];
			for (int i = 0; i < paramTypes.length; i++) {
				String paramTypeName = paramTypes[i].getSimpleName(); // ---------------------------------------
				String paramName = Character.toLowerCase(paramTypeName.charAt(0)) + paramTypeName.substring(1);
				paramBeans[i] = getBean(beanName);
			}
			bean = constructor.newInstance(paramBeans);
			
		}
		context.put(beanName,  bean);
		return bean;
	}
}
