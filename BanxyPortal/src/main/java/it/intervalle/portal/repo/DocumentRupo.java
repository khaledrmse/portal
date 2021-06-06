package it.intervalle.portal.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.intervalle.portal.entity.Document;
@Repository
public interface DocumentRupo extends CrudRepository<Document, Long> {

}
