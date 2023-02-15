package com.deguzman.DeGuzmanStuffAnywhere.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.SongDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SongListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateSongTitleException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.SongJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.Song;

@Service
public class SongService {

	@Autowired
	private SongDaoImpl songDaoImpl;
	
	@Autowired
	private SongJpaDao songJpaDao;
	
	public SongListResponse findAllSongInformation() {
		SongListResponse response = new SongListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Song> list = songDaoImpl.findAllSongInformation();
		
		response.setList(list);
		return response;
	}

	public ResponseEntity<Map<String, Object>> getAllSongsPagination(@RequestParam(required = false) String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		try {

			List<Song> shop = songJpaDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<Song> pageBooks = null;

			if (title == null) {
				pageBooks = songJpaDao.findAll(paging);
			} else {
				// pageBooks = autoShopDao.findByNameContaining(autoShopname, paging);
			}

			shop = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("songs", shop);
			response.put("currentPage", pageBooks.getNumber());
			response.put("totalItems", pageBooks.getTotalElements());
			response.put("totalPages", pageBooks.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public SongListResponse findSongByArtist(@PathVariable String artist) {
		SongListResponse response = new SongListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Song> list = songDaoImpl.findSongByArtist(artist);
		
		response.setList(list);
		return response;
	}
	
	public SongListResponse findSongsByGenre(@PathVariable String genre) {
		SongListResponse response = new SongListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Song> list = songDaoImpl.findSongsByGenre(genre);
		
		response.setList(list);
		return response;
	}
	
	public ResponseEntity<com.deguzman.DeGuzmanStuffAnywhere.model.Song> findSongById(int song_id) throws ResourceNotFoundException {
		return songDaoImpl.findSongById(song_id);
	}
	
	public ResponseEntity<com.deguzman.DeGuzmanStuffAnywhere.model.Song> findSongByTitle(String title) {
		return songDaoImpl.findSongByTitle(title);
	}
	
	public int findSongCount() {
		return songDaoImpl.findSongCount();
	}
	
	public int addSongInformation(com.deguzman.DeGuzmanStuffAnywhere.model.Song song) throws DuplicateSongTitleException {
		return songDaoImpl.addSongInformation(song);
	}
	
	public int updateSongInformation(int song_id, com.deguzman.DeGuzmanStuffAnywhere.model.Song songDetails) {
		return songDaoImpl.updateSongInformation(song_id, songDetails);
	}
	
	public int deleteSongInformation(int song_id) {
		return songDaoImpl.deleteSongInformation(song_id);
	}
	
	public int deleteAllSongs() {
		return songDaoImpl.deleteAllSongs();
	}
}
