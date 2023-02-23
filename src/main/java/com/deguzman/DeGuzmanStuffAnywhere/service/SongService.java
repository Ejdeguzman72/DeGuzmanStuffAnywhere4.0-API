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
import org.springframework.web.bind.annotation.RequestParam;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.SongDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByDescr;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByIntRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByNameRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SongAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SongAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SongListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SongSearchResponse;
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
	
	public SongListResponse findSongByArtist(SearchByNameRequest request) {
		SongListResponse response = new SongListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Song> list = songDaoImpl.findSongByArtist(request.getName());
		
		response.setList(list);
		return response;
	}
	
	public SongListResponse findSongsByGenre(SearchByDescr request) {
		SongListResponse response = new SongListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Song> list = songDaoImpl.findSongsByGenre(request.getDescr());
		
		response.setList(list);
		return response;
	}
	
	public SongSearchResponse findSongById(SearchByIntRequest request) throws ResourceNotFoundException {
		SongSearchResponse response = new SongSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Song song = songDaoImpl.findSongById(request.getId());
		
		response.setSong(song);
		return response;
	}
	
	public SongSearchResponse findSongByTitle(SearchByNameRequest request) {
		SongSearchResponse response = new SongSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Song song = songDaoImpl.findSongByTitle(request.getName());
		
		response.setSong(song);
		return response;
	}
	
	public int findSongCount() {
		return songDaoImpl.findSongCount();
	}
	
	public SongAddUpdateResponse addSongInformation(SongAddUpdateRequest request) throws DuplicateSongTitleException {
		SongAddUpdateResponse response = new SongAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Song song = null;
		int count = 0;
		
		count = songDaoImpl.addSongInformation(request);
		if (count > 0) {
			song.setArtist(request.getArtist());
			song.setGenre(request.getGenre());
			song.setTitle(request.getTitle());
			if (song != null) {
				response.setSong(song);
			}
		}
		
		return response;
	}
	
	public SongAddUpdateResponse updateSongInformation(SongAddUpdateRequest request) throws ResourceNotFoundException {
		SongAddUpdateResponse response = new SongAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Song song = songDaoImpl.findSongById(request.getSong_id());
		int count = 0;
		
		count = songDaoImpl.updateSongInformation(request.getSong_id(),request);
		if (count > 0) {
			song.setArtist(request.getArtist());
			song.setGenre(request.getGenre());
			song.setTitle(request.getTitle());
			if (song != null) {
				response.setSong(song);
			}
		}
		
		return response;
	}
	
	public int deleteSongInformation(SearchByIntRequest request) {
		return songDaoImpl.deleteSongInformation(request.getId());
	}
	
	public DeleteAllResponse deleteAllSongs() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = songDaoImpl.deleteAllSongs();
		
		response.setCount(count);
		return response;
	}
}
