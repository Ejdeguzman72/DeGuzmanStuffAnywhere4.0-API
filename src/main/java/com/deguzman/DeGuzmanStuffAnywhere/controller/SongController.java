package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.deguzman.DeGuzmanStuffAnywhere.service.SongService;

@RestController
@RequestMapping("/app/music")
@CrossOrigin
public class SongController {

	@Autowired
	private SongService songService;

	@GetMapping("/all")
	@CrossOrigin
	public SongListResponse getAllSongInformation() {
		SongListResponse response = songService.findAllSongInformation();
		return response;
	}

	@GetMapping("all-songs")
	@CrossOrigin
	public ResponseEntity<Map<String, Object>> getAllSongsPagination(@RequestParam(required = false) String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return songService.getAllSongsPagination(title, page, size);
	}

	@GetMapping("/song/{song_id}")
	@CrossOrigin
	public SongSearchResponse getSongInformationById(@RequestBody @Valid SearchByIntRequest request) throws ResourceNotFoundException {
		SongSearchResponse response =songService.findSongById(request);
		return response;
	}

	@GetMapping("/song/artist/{artist}")
	@CrossOrigin
	public SongListResponse getSongInformationByArtist(@RequestBody @Valid SearchByNameRequest request) {
		SongListResponse response = songService.findSongByArtist(request);
		return response;
	}

	@GetMapping("/song/genre/{genre}")
	@CrossOrigin
	public SongListResponse getSongInformationByGenre(@RequestBody @Valid SearchByDescr request) {
		SongListResponse response =  songService.findSongsByGenre(request);
		return response;
	}

	@GetMapping("/count-of-songs")
	@CrossOrigin
	public int getCountOfSongs() {
		return songService.findSongCount();
	}

	@PostMapping("/add-song-information")
	@CrossOrigin
	public SongAddUpdateResponse addSongInformation(@RequestBody @Valid SongAddUpdateRequest request) throws DuplicateSongTitleException {
		SongAddUpdateResponse response = songService.addSongInformation(request);
		return response;
	}
	
	@PutMapping("/song/{song_id}")
	@CrossOrigin
	public SongAddUpdateResponse updateSongInformation(@RequestBody @Valid SongAddUpdateRequest request) throws ResourceNotFoundException {
		SongAddUpdateResponse response = songService.updateSongInformation(request);
		return response;
	}

	@DeleteMapping("/song/{song_id}")
	@CrossOrigin
	public int deleteSongInformationById(@RequestBody @Valid SearchByIntRequest request) {
		return songService.deleteSongInformation(request);
	}

	@DeleteMapping("/delete-all-songs")
	@CrossOrigin
	public DeleteAllResponse deleteAllSongs() {
		DeleteAllResponse response = songService.deleteAllSongs();
		return response;
	}
}
