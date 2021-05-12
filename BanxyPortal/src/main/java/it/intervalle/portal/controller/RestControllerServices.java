package it.intervalle.portal.controller;

 
import java.util.ArrayList;
import java.util.Arrays;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import it.intervalle.portal.entity.Client;
import it.intervalle.portal.entity.ResponseMessage;
import it.intervalle.portal.repo.Crudrepo;

import it.intervalle.portal.service.FilenetP8Services;
import it.intervalle.portal.service.FilenetService;
import it.intervalle.portal.service.FilesStorageService;

@RestController

public class RestControllerServices {
	public final static String DOCMINETYPEPDF = "application/pdf";
	 @Autowired
	 private Crudrepo crudrepo;
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	 @Autowired
	  FilesStorageService storageService;
	 @Autowired
	 FilenetService filenetService;
	 @Autowired
	 FilenetP8Services filenetP8service;

	 
	
	 
	@PostMapping("/api/client")
	public Client  register(@RequestBody Client client)
	{
		 client.setPassword( bCryptPasswordEncoder.encode(client.getPassword()));
		crudrepo.save(client);
		
		return client;
		
	 
	}
	
	 @GetMapping(value = "/api/clients", produces = { "application/json", "application/xml" }  )
	    public List<Client> getUsers() {
	        return (List<Client>) crudrepo.findAll();
	    }
	 @PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
		 String message = "";
	    try {
	      List<String> fileNames = new ArrayList<>();

	      Arrays.asList(files).stream().forEach(file -> {
	       try {
	    	   filenetP8service.uploadfiletoP8 (filenetService.getOs(), file.getOriginalFilename(), "/test",file.getBytes() , DOCMINETYPEPDF);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        storageService.save(file);
	        System.out.println("file"+file.getOriginalFilename());
	        fileNames.add(file.getOriginalFilename());
	      });

	      message = "Uploaded the files successfully: " + fileNames;
	     
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Fail to upload files!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
	 
	
}
