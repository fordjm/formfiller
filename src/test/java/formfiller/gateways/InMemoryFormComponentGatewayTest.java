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
	
	@Test(expected = InMemoryFormComponentGateway.NullSave.class)
	public void cannotSaveNull(){
		gateway.save(null);
	}
	
	@Test(expected = InMemoryFormComponentGateway.NullFind.class)
	public void cannotFindNull(){
		gateway.find(null);
	}
	
	@Test
	public void canSaveAndFind() {
		FormComponent mockFormComponent = FormComponentMocker.makeMockFormComponent("id");
		
		gateway.save(mockFormComponent);
		
		assertThat(gateway.find("id"), is(mockFormComponent));
	}

}
