package com.niit.binder.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="users")
@Component
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	/* declare the database column names for User... */
	
	@Id
	@Length(min = 6, max = 11, message = "Id should contain 6-11 characters")
	private String id;
	
	@NotBlank(message = "Name field can not be blank")
	@Length(min = 3 , message = "Name contains atleast 3 character")
	private String name;
	
	@NotBlank(message="Please select a password")
	@Length(min=5, max=15, message="Password should be between 5 - 15 charactes")
	private String password;
	
	@NotBlank
	private String gender;
	
	@Email(message="Please provide a valid email address")
	private String email;
	
	@Pattern(regexp="(^$|[0-9]{10})", message = "Please provide a valid phone no.")
	private String phone;
	
	private String role;
	
	private String image;
	
	private String photos;
	
	@Transient
	private MultipartFile file;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	Set<Blog> blog;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	Set<Forum> forum;

	/* getters/setters for all the fields taken... */
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
}
