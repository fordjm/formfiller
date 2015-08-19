package formfiller.boundaryCrossers;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class PresentableAnswerTest {
	private PresentableAnswer presentableAnswer;
	
	@Before
	public void setUp() {
		presentableAnswer = new PresentableAnswer();
	}
	@Test
	public void gettingIdReturnsNegativeOne() {
		assertThat(presentableAnswer.getId(), is(-1));
	}
	
	public class GivenAnId {
		@Before
		public void givenanId(){
			presentableAnswer.setId(0);
		}
		@Test
		public void gettingIdReturnsGivenId(){
			assertThat(presentableAnswer.getId(), is(0));
		}
	}

}
