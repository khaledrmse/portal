package it.intervalle.portal.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
 

@Service
public class FilenetService {
 
private String uri;
private String username;
private String password;
private String context;
private String os;
public FilenetService(@Value("${filenet.uri}") String uri, @Value("${filenet.username}") String username,
		              @Value("${filenet.password}") String password,
		              @Value("${filenet.context}")  String context,
		              @Value("${filenet.os}")  String os) {
  
	this.uri = uri;
	this.username = username;
	this.password = password;
	this.context = context;
	this.os      =os;
}
public String getOs() {
	return os;
}
public void setOs(String os) {
	this.os = os;
}
public String getUri() {
	return uri;
}
public void setUri(String uri) {
	this.uri = uri;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getContext() {
	return context;
}
public void setContext(String context) {
	this.context = context;
}

 

}
