package it.intervalle.portal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.intervalle.portal.entity.Client;
import it.intervalle.portal.repo.Crudrepo;
@Service
public class MuserDetailsServices implements UserDetailsService {
@Autowired
Crudrepo crudrepo;

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Optional<Client>client=crudrepo.findByMail(mail);
		client.orElseThrow(()-> new UsernameNotFoundException("client with mail"+mail+"not found"));
	      return   client.map(MuserDetails::new).get();
	}

}
