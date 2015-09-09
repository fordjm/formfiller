package formfiller.gateways;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.utilities.FormComponentMocker;

public class InMemoryFormComponentGatewayTest {
	private InMemoryFormComponentGateway gateway;

	private FormComponent makeMockFormComponentWithId(String id) {
		return FormComponentMocker.makeMockFormComponent(id);
	}

	private void saveFormComponentsAtGateway(FormComponent... formComponents) {
		for (FormComponent formComponent : formComponents)
			gateway.save(formComponent);
	}

	@Before
	public void setUp() {
		gateway = new InMemoryFormComponentGateway();
	}
	
	@Test
	public void saveCanHandleNull(){
		gateway.save(null);
	}
	
	@Test
	public void saveCanHandleNullObject(){
		gateway.save(NullFormComponents.NULL);
	}
	
	@Test
	public void findCanHandleNull(){
		FormComponent result = gateway.find(null);
		
		assertThat(result, is(NullFormComponents.NULL));
	}
	
	@Test
	public void canSaveAndFindGivenFormComponents() {
		FormComponent mockComponent0 = makeMockFormComponentWithId("id0");
		FormComponent mockComponent1 = makeMockFormComponentWithId("id1");
		
		saveFormComponentsAtGateway(mockComponent0, mockComponent1);
		
		assertThat(gateway.find("id0"), is(mockComponent0));
		assertThat(gateway.find("id1"), is(mockComponent1));
	}
}
