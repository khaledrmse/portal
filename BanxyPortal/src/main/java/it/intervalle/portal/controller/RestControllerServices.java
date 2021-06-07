package it.intervalle.portal.controller;

 
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
 
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.intervalle.portal.entity.Client;
import it.intervalle.portal.entity.ResponseMessage;
import it.intervalle.portal.repo.Crudrepo;

import it.intervalle.portal.service.FilenetP8Services;
import it.intervalle.portal.service.FilenetService;
import it.intervalle.portal.service.FilesStorageService;
import it.intervalle.portal.service.MailService;

@RestController()
public class RestControllerServices {
	public final static String DOCMINETYPEPDF = "application/pdf";
	 @Autowired
	 private Crudrepo crudrepo;
	
	 @Autowired
	  FilesStorageService storageService;
	 @Autowired
	 FilenetService filenetService;
	    @Autowired
	    private MailService mailService;
	 @Autowired
	 FilenetP8Services filenetP8service;

	 
	
	 
	@PostMapping("api/client")
	public Client  register(@RequestBody Client client) throws UnsupportedEncodingException, MessagingException
	{
	 
		mailService.generateOneTimePassword(client);
		
		return client;
		
	 
	}
	
	 @Operation(security = @SecurityRequirement(name = "basicAuth"))
	 @GetMapping(value = "api/clients", produces = {"application/json"} )
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
