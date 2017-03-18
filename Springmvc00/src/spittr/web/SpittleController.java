package spittr.web;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.Spittle;
import spittr.data.SpittleRepository;
 
@Controller
@RequestMapping("/spittles")
public class SpittleController {
	private SpittleRepository spittleRepository;
	private static final String MAX_LONG_AS_STRING = Long.toString(Long.MAX_VALUE);
	
	@Autowired
	public SpittleController(SpittleRepository spittleRepository){
		this.spittleRepository = spittleRepository;
	}
	
	public SpittleController() {
	}

	@RequestMapping(method=RequestMethod.GET)
	public String spittles( 
			@RequestParam(value="max") long max, 
			@RequestParam(value="count") int count, Model model){
		model.addAttribute("spittleList",spittleRepository.findSpittles(max, count));
		return "spittles";
	}
	
	@RequestMapping(value="/{spittleId}",method=RequestMethod.GET)
	public String spittle(
			@PathVariable("spittleId") long spittleId, Model model){
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}
	
	
}
