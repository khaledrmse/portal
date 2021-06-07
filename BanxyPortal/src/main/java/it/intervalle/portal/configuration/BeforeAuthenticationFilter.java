package it.intervalle.portal.configuration;


import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import it.intervalle.portal.entity.Client;
import it.intervalle.portal.entity.Role;
import it.intervalle.portal.service.MailService;
import it.intervalle.portal.service.MuserDetailsServices;
 

 
//@Component
public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	Logger log = LoggerFactory.getLogger(BeforeAuthenticationFilter.class);
	@Autowired
	private MailService mailService;
	@Autowired
	MuserDetailsServices userservice;
	
	//@Autowired
	@Override
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		// TODO Auto-generated method stub
		super.setAuthenticationManager(authenticationManager);
	}
//	@Autowired
	@Override
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
		// TODO Auto-generated method stub
		super.setAuthenticationSuccessHandler(successHandler);
	}
	//@Autowired
	@Override
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
		// TODO Auto-generated method stub
		super.setAuthenticationFailureHandler(failureHandler);
	}

	public BeforeAuthenticationFilter() {
        setUsernameParameter("mail");
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }
     
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
         
        String email = request.getParameter("mail");
        Client client=userservice.findClientByMail(email);
        Set<Role> role = client.getRoles();
      
        boolean isadmin =role.stream().anyMatch(r->r.getName().equals("ADMIN"));
        log.info("isadmin "+isadmin);
        log.info("The user " + email + " is about to login");
        if(client !=null)
        {
            log.info("The user " + email + " is about to login*******************");
        	   if(mailService.ExpiredOTP(client)&& !isadmin)
        	   {
        	        log.info("The user " + client.getFirtsname()+"with mail" +client.getMail()+ " password Cleared");
        		   mailService.clearOTP(client);
        		   
        	   }
        }
        else
        	
            log.info("The user " + email + " is about to login");
         
     
         
        return super.attemptAuthentication(request, response);
    }
}
