package spittr.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.Spittle;
import spittr.data.SpittleRepository;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.matchers.*;

public class SpittleControllerTest {
	@Test
	public void shouldShowRecentSpittles()throws Exception{
		List<Spittle> expectedSpittles = createSpittleList(20);
		
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(238900, 20)).thenReturn(expectedSpittles);
		
		SpittleController SpittleController =new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(SpittleController).setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(get("/spittles?max=238900&count=50")).andExpect(view().name("spittles")).andExpect(model().attributeExists("spittleList")).andExpect(model().attribute("spittleList",JUnitMatchers.hasItems(expectedSpittles.toArray())));
	}
	
	@Test
	public void shouldShowSpittle()throws Exception{
		Spittle spittle = new Spittle("Hello", new Date());
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findOne(12345)).thenReturn(spittle);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/spittles/12345")).andExpect(view().name("spittle")).andExpect(model().attributeExists("spittle")).andExpect(model().attribute("spittle", spittle));
	}
	

	private List<Spittle> createSpittleList(int count){
		List<Spittle> spittles = new ArrayList<>();
		for(int i=0;i<count;i++){
			spittles.add(new Spittle("Spittle "+i, new Date()));
		}
		return spittles;
	}
}
