package it.intervalle.portal.configuration;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import it.intervalle.portal.service.CEConnection;
import it.intervalle.portal.service.MuserDetailsServices;
 
@EnableWebSecurity
public class Securityconfiguration  {
	 @Configuration
	    @Order(2)
	    public static class ApiWebSecurityConfigurationAdapter2 extends WebSecurityConfigurerAdapter {
		   // @Autowired
		    //private  BeforeAuthenticationFilter beforeAuthenticationFilter;	   
		    @Autowired
		    private MuserDetailsServices userDetailsService;
		    @Autowired
		    private LoggingAccessDeniedHandler accessDeniedHandler;
		   // @Autowired
		   // private LoginSuccessHandler loginSuccessHandler;
		   // @Autowired
		   // private LoginFailureHandler loginFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		   .authorizeRequests()
		   
           .antMatchers(
                   "/",
                   "/css/**",
                   "/js/**",
                   "/img/**",
                   "/swagger-ui.html/**",
                   "/webjars/**").permitAll()
            .antMatchers("AUTH_LIST").permitAll()
            .antMatchers("/load/**","/upload/**").hasAnyAuthority("USER","ADMIN")
            .anyRequest().authenticated()
       .and()
      // .addFilterBefore(beforeAuthenticationFilter, BeforeAuthenticationFilter.class)
      
       .formLogin()
       		.loginPage("/login")
       		.usernameParameter("mail")
       	
       		.permitAll()
       .and()
       .logout()
           .invalidateHttpSession(true)
           .clearAuthentication(true)
           .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
           .logoutSuccessUrl("/login?logout")
           .permitAll()
       .and()
       .exceptionHandling()
           .accessDeniedHandler(accessDeniedHandler)
           
        .and()
        .sessionManagement()
        .maximumSessions(1)
        .expiredUrl("/login?invalide-session=true");
		 
	}
	
	   
	
	
	
	
	@Override
		public void configure(WebSecurity web) throws Exception {
		  web.ignoring().antMatchers(
		
				  "/v2/api-docs",
                  "/configuration/ui",
                  "/swagger-resources/**",
                  "/configuration/security",
                  "/swagger-ui.html",
                  "/webjars/**");
		}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		     auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		   
	}
 
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	/* @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }*/
	     
	
	 
}
	 @Configuration
	    @Order(1)
	    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
			@Autowired
			MuserDetailsServices userDetailsService;
	
	
			 @Autowired
			private BCryptPasswordEncoder bCryptPasswordEncoder;
			@Override
			protected void configure(AuthenticationManagerBuilder auth) throws Exception {
				     auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
				   
			}
			@Override
			public void configure(WebSecurity web) throws Exception {
			  web.ignoring().antMatchers(
		
					  "/v2/api-docs",
	                  "/configuration/ui",
	                  "/swagger-resources/**",
	                  "/configuration/security",
	                  "/swagger-ui.html",
	                  "/webjars/**");
			}
	        protected void configure(HttpSecurity http) throws Exception {
	           http 
	           		.antMatcher("/api/**")
	                 .csrf().disable()
			          .authorizeRequests()    
			          .antMatchers("AUTH_LIST").permitAll()
	                  .antMatchers("/api/clients/**",
	                		        "/api/client/**")
	                  .hasAnyAuthority("ADMIN").anyRequest().authenticated()
	                 .and()
	                 .httpBasic();
	        }
	        
	        
	        
	        
	    	@Bean
		    public CEConnection ceConnection() {
		        return new CEConnection();
		    }
	    	
	    }
	

	
	
		
		
		
		
		
	
		
}
