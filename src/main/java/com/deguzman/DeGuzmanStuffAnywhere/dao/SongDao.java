package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateSongTitleException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.Song;

public interface SongDao {

	public List<Song> findAllSongInformation();

	public List<Song> findSongByArtist(String artist);

	public List<Song> findSongsByGenre(String genre);

	public ResponseEntity<Song> findSongById(int song_id) throws ResourceNotFoundException;

	public ResponseEntity<Song> findSongByTitle(String title);

	public int findSongCount();

	public int addSongInformation(Song song) throws DuplicateSongTitleException;

	public int updateSongInformation(int song_id, Song songDetails);

	public int deleteSongInformation(int song_id);

	public int deleteAllSongs();
}
