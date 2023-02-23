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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.domain.PostAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.PostAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.PostListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByLongRequest;
import com.deguzman.DeGuzmanStuffAnywhere.service.PostsService;

@RestController
@RequestMapping("/app/posts")
@CrossOrigin
public class PostController {
	
	@Autowired
	private PostsService socialMediaService;
	
	@GetMapping("/all")
	public PostListResponse getAllPosts() {
		PostListResponse response = socialMediaService.findAllPosts();
		return response;
	}
	
	@GetMapping("/all-posts")
	public ResponseEntity<Map<String, Object>> findAllPostsPagination(
			@RequestParam(required = false) String content, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return socialMediaService.findAllPostsPagination(content, page, size);
	}
	
	@GetMapping("/user/{user_id}")
	public PostListResponse getPostsByUser(@RequestBody @Valid SearchByLongRequest request) {
		PostListResponse response = socialMediaService.findPostsByUser(request);
		return response;
	}
	
	@PostMapping("/add-post")
	public PostAddUpdateResponse addPostInformation(@RequestBody @Valid PostAddUpdateRequest request) {
		PostAddUpdateResponse response = socialMediaService.addPost(request);
		return response;
	}
	
	@DeleteMapping("/post/{post_id}")
	public int deletePostInformation(@PathVariable int post_id) {
		return socialMediaService.deletePost(post_id);
	}
}
