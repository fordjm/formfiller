package formfiller.gateways;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.FormComponent;
import formfiller.utilities.FormComponentMocker;

public class InMemoryFormComponentGatewayTest {
	private InMemoryFormComponentGateway gateway;

	@Before
	public void setUp() {
		gateway = new InMemoryFormComponentGateway();
	}
	
	@Test
	public void saveCanHandleNull(){
		gateway.save(null);
	}
	
	@Test
	public void findCanHandleNull(){
		FormComponent result = gateway.find(null);
		
		assertNull(result);
	}
	
	@Test
	public void canSaveAndFindGivenFormComponent() {
		FormComponent mockFormComponent = FormComponentMocker.makeMockFormComponent("id");
		
		gateway.save(mockFormComponent);
		
		assertThat(gateway.find("id"), is(mockFormComponent));
	}
}
