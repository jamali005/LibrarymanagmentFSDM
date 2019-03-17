package org.fsdm.securite;



import org.fsdm.dao.UsersRepository;
import org.fsdm.entities.RespBib;
import org.fsdm.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecuriteController {
	@Autowired
	private UsersRepository usersRepository;
	
	@RequestMapping(value="/login")
	public String login(){
	return "login";
	}
	@RequestMapping(value="/")
	public String home(){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Users users=usersRepository.findUserByUsername(name);
		if(users instanceof RespBib){
			return "redirect:/Admin/";
		}
		 return "redirect:/Documents/Index";
				
	}
	@RequestMapping(value = "/403")
	public String inacces() {

		return "403";
	}
	
}
