package com.finance.rksamples.fincalmicroservice.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	private String description;
	
	//no setters and getters for User. (One User --> Can do Many --> Posts)
	@ManyToOne(fetch=FetchType.LAZY)//This won't retrieve User details Unless you call -> post.getUser()
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("Post [id=%s, description=%s, user=%s]", id, description, user);
	}
	
	
	
	
}
