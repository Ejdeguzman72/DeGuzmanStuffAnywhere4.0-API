package com.deguzman.DeGuzmanStuffAnywhere.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class Books {

	public int book_id;
	
	@NotNull(message = "Invalid request, title field is missing/null")
	@Pattern(regexp = "[a-zA-Z,0-9]{0,50}", message = "Invalid request, title field can only contain letters, special characters are not allowed")
	public String title;
	
	@NotNull(message = "Invalid request, author field is missing/null")
	@Pattern(regexp = "[a-zA-Z]{0,50}", message = "Invalid request, author field can only contain letters, numbers, special characters are not allowed")
	public String author;
	
	@NotNull(message = "Invalid request, descr field is missing/null")
	@Pattern(regexp = "[a-zA-Z,0-9]{0,200}", message = "Bad request, descr field can only contain letters, special characters are not allowed")
	public String descr;

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + book_id;
		result = prime * result + ((descr == null) ? 0 : descr.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Books other = (Books) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (book_id != other.book_id)
			return false;
		if (descr == null) {
			if (other.descr != null)
				return false;
		} else if (!descr.equals(other.descr))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Books [book_id=" + book_id + ", title=" + title + ", author=" + author + ", descr=" + descr + "]";
	}

	public Books(int book_id, String title, String author, String descr) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.author = author;
		this.descr = descr;
	}

	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}

}
