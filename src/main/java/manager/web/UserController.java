package manager.web;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Dictionary;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import manager.user.User;
import manager.user.UserRepository;


@RestController
@RequestMapping(path="/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path="/user")
	public @ResponseBody String addNewUser(@RequestParam String name, 
			@RequestParam String email, @RequestParam String dob) {
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setDob(LocalDate.parse(dob, dateFormat));
		userRepository.save(user);
		return "Saved";
	}
	
	@GetMapping(path="/user/{n}")
	public @ResponseBody Iterable<User> getUsersByLimit(@PathVariable String n) {
		
		return userRepository.findAll(PageRequest.of(0, Integer.parseInt(n))).getContent();
	}
	
	@GetMapping(path="/user/ages/{n}")
	public @ResponseBody Dictionary<String, Integer> getUsersAgesGtLimit(@PathVariable String n) {
		
		Dictionary<String, Integer> detail = new Hashtable<String, Integer>();
		
		userRepository.findAll().forEach(user -> {
			int age = Period.between(user.getDob(), LocalDate.now()).getYears();
			if(age > Integer.parseInt(n))
				detail.put(user.getName(), age); 
		});
		
		return detail;
	}
}
