package pizza.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repositoryTestContext.xml"})
public class InMemTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Test
	public void emptyMethod() {
		
	}

}