package it.intervalle.portal.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.*;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int 	user_id;
	private String 	firtsname;
	private String  mail;
	private String 	lastname;
	private String 	password;
	private Date   	timepassword;
	private boolean locked;
	private boolean enabled;
	  @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName="role_id")
            )
    private Set<Role> roles = new HashSet<>();
	  
	  
	  



	public int getId() {
		return user_id;
	}

    
	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public String getFirtsname() {
		return firtsname;
	}


	public void setFirtsname(String firtsname) {
		this.firtsname = firtsname;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


 
 

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getTimepassword() {
		return timepassword;
	}

	public void setTimepassword(Date timepassword) {
		this.timepassword = timepassword;
	}

	public boolean getLocked() {
		return locked;
	}


	public void setLocked(Boolean locked) {
		this.locked = locked;
	}


	public boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	@Override
	public String toString() {
		return "Client [id=" + user_id + ", firtsname=" + firtsname + ", mail=" + mail + ", lastname=" + lastname
				+ ", password=" + password  + ", timepassword=" + timepassword + ", locked=" + locked
				+ ", enabled=" + enabled + "]";
	}


}
