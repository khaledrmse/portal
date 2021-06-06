package it.intervalle.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.intervalle.portal.entity.Document;
import it.intervalle.portal.repo.DocumentRupo;

@Service
public class DocumentService {
	@Autowired
	private DocumentRupo documentRupo;
	
	 void addDocument(Document document)
	{
		documentRupo.save(document);
	}

}
