package it.intervalle.portal.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import it.intervalle.portal.entity.Client;
import it.intervalle.portal.service.MailService;
import it.intervalle.portal.service.MuserDetails;
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Autowired
    private MailService mailService;
     
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {
         
         
        //MuserDetails clientDetails
             //   = (MuserDetails) authentication.getPrincipal();
                 
         //Client client = clientDetails.getClient();
         
     
        //    mailService.clearOTP(client);
        
         
        super.onAuthenticationSuccess(request, response, authentication);
    }
 
}
