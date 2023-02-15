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

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.PostDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.PostAddRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.PostListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.PostDTO;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.PostsJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.Post;

@Service
public class PostsService {

	@Autowired
	private PostDaoImpl postDaoImpl;
	
	@Autowired
	private PostsJpaDao postDao;
	
	public PostListResponse findAllPosts() {
		PostListResponse response = new PostListResponse();
		List<PostDTO> list = postDaoImpl.findAllPosts();
		
		response.setList(list);
		return response;
	}
	
	public ResponseEntity<Map<String, Object>> findAllPostsPagination(
			@RequestParam(required = false) String content, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {

			List<Post> shop = postDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<Post> pageBooks = null;

			if (content == null) {
				pageBooks = postDao.findAll(paging);
			} else {
				// pageBooks = autoShopDao.findByNameContaining(autoShopname, paging);
			}

			shop = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("posts", shop);
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
	
	public PostListResponse findPostsByUser(long user_id) {
		PostListResponse response = new PostListResponse();
		List<PostDTO> list = postDaoImpl.findPostsByUser(user_id);
		
		response.setList(list);
		return response;
	}
	
	public int addPost(PostAddRequest request) {
		return postDaoImpl.addPost(request);
	}
	
	public int deletePost(int post_id) {
		return postDaoImpl.deletePost(post_id);
	}
}
