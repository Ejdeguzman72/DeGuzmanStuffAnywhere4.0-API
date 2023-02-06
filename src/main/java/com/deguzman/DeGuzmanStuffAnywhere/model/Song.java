package com.deguzman.DeGuzmanStuffAnywhere.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class Song {

	public int song_id;
	
	@NotNull(message = "title field is missing/null")
	@Pattern(regexp = "[a-zA-Z]{0,50}", message = "Bad request, title field can only contain letters, numbers/special characters are not allowed")
	public String title;
	
	@NotNull(message = "artist field is missing/null")
	@Pattern(regexp = "[a-zA-Z]{0,50}", message = "Bad request, artist field can only contain letters, numbers/special characters are not allowed")
	public String artist;
	
	@NotNull(message = "genre field is missing/null")
	@Pattern(regexp = "[a-zA-Z]{0,50}", message = "Bad request, genre field can only contain letters, numbers/special characters are not allowed")
	public String genre;

	public int getSong_id() {
		return song_id;
	}

	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + song_id;
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
		Song other = (Song) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (song_id != other.song_id)
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
		return "Song [song_id=" + song_id + ", title=" + title + ", artist=" + artist + ", genre=" + genre + "]";
	}

	public Song(int song_id, String title, String artist, String genre) {
		super();
		this.song_id = song_id;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
	}

	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}

}
