package spittr.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.junit.Test;
import static org.mockito.Mockito.*;

import org.springframework.test.web.servlet.MockMvc;

import spittr.Spitter;
import spittr.data.SpitterRepository;

public class SpitterControllerTest {
	@Test
	public void shouldShowRigstration() throws Exception{
		SpitterController controller = new SpitterController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
	}
	
	@Test
	public void shouldProcessRegistration() throws Exception{
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("lee", "peikun", "paddy", "123");
		Spitter saved = new Spitter(24L,"lee", "peikun", "paddy", "123");
		when(mockRepository.save(unsaved)).thenReturn(saved);
		SpitterController spitterController = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(spitterController).build();
		mockMvc.perform(post("/spitter/register").param("firstName","lee").param("lastName","peikun").param("username","paddy").param("password","123")).andExpect(redirectedUrl("/spitter/paddy"));
		verify(mockRepository,atLeastOnce()).save(unsaved);
	}
	
}
