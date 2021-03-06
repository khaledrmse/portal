package it.intervalle.portal.service;

import java.util.ArrayList;
 
import java.util.Collection;
import java.util.List;
import java.util.Set;
 

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.intervalle.portal.entity.Client;
import it.intervalle.portal.entity.Role;

 

 

public class MuserDetails implements UserDetails {
private Client client;
public MuserDetails(Client client) {
	 this.client=client;
}

	

	public Client getClient() {
	return client;
}



public void setClient(Client client) {
	this.client = client;
}



	public MuserDetails() {
		super();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 Set<Role> roles = client.getRoles();
		    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		     
		    for (Role role : roles) {
		        authorities.add(new SimpleGrantedAuthority(role.getName()));
		    }
		     
		     
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return client.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return client.getMail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return client.getEnabled();
	}

}
