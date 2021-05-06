package it.intervalle.portal.controller;

 
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
 

@Controller
public class Maincontroller {
	
	@GetMapping("/")
	public String home()
	{
		return "index.html";
	}
	
	 @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
	@GetMapping("/load")
	public String user()
	{
		return "upload.html";
	}
	
	
	
	 @GetMapping("/access-denied")
	    public String accessDenied() {
	        return "/error/access-denied";
	    }


	
}
