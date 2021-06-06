package it.intervalle.portal.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int document_id;
	private String document_name;
	private String document_path;
	private Date upload_date;
	@OneToOne
	@JoinColumn(name="user_id")
	private Client client;
	
	
	public int getId() {
		return document_id;
	}
	public void setId(int id) {
		this.document_id = id;
	}
	public String getDocument_name() {
		return document_name;
	}
	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}
	public String getDocument_path() {
		return document_path;
	}
	public void setDocument_path(String document_path) {
		this.document_path = document_path;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	

}
