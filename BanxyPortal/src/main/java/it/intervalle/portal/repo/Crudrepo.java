package it.intervalle.portal.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.intervalle.portal.entity.Client;

 


 
@Repository
public interface Crudrepo extends CrudRepository<Client, Long> {
Optional<Client> findByMail(String mail);

}
