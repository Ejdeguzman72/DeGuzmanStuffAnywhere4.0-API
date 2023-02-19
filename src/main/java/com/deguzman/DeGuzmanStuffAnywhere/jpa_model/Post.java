package com.deguzman.DeGuzmanStuffAnywhere.jpa_model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.deguzman.DeGuzmanStuffAnywhere.authentication_models.User;

@Entity
@Table(name = "posts")
@CrossOrigin
public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6929244243457370294L;
	private int postId;
	private String content;
	private LocalDate createdDate;
	
	public User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "createdDate")
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + postId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (postId != other.postId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", content=" + content + ", createdDate=" + createdDate + "]";
	}
	public Post(int postId, String content, LocalDate createdDate) {
		super();
		this.postId = postId;
		this.content = content;
		this.createdDate = createdDate;
	}
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
