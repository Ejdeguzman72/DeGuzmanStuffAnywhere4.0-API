package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.deguzman.DeGuzmanStuffAnywhere.dao.PostDao;
import com.deguzman.DeGuzmanStuffAnywhere.dto.PostDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.Post;

@Repository
public class PostDaoImpl implements PostDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PostDaoImpl.class);

	String GET_ALL_POSTS = "SELECT POST_ID, CONTENT, CREATEDDATE, USERNAME FROM POSTS P, USERS US WHERE P.USER_ID = US.USER_ID";
	
	String GET_POSTS_BY_USER = "SELECT P.POST_ID, P.CONTENT, P.CREATEDDATE, US.USERNAME FROM POSTS P, USERS US WHERE P.USER_ID = US.USER_ID AND US.USER_ID = ?";
	
	String ADD_POST = "INSERT INTO POSTS " + 
			"(content, createddate, user_id) " + 
			"VALUES(?, ?, ?)";
	
	String DELETE_POST = "DELETE FROM POST WHERE POST_ID = ?";
	
	@Override
	@Cacheable(value = "postList")
	public List<PostDTO> findAllPosts() {
		
		List<PostDTO> list = new ArrayList<>();
		
		try {
			list = jdbcTemplate.query(GET_ALL_POSTS, BeanPropertyRowMapper.newInstance(PostDTO.class));
			
			LOGGER.info("Retrieving all posts...");			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return list;
	}

	@Override
	public List<PostDTO> findPostsByUser(long user_id) {
		List<PostDTO> list = new ArrayList<>();
		
		try {
			list = jdbcTemplate.query(GET_POSTS_BY_USER, (rs,rowNum) -> new PostDTO(rs.getInt("POST_ID"), 
					rs.getString("CONTENT"), 
					rs.getDate("CREATEDDATE"), 
					rs.getString("USERNAME")), user_id);
			
			LOGGER.info("Retrieving post for user_id:" + " " + user_id);
						
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return list;
	}

	@Override
	@CachePut(value = "postList")
	public int addPost(Post post) {

		int count = 0;
		
		try {
			String content = post.getContent();
			Date createdDate = post.getCreatedDate();
			int userId = post.getUser_id();
			
			LOGGER.info("Adding post information...");
			
			count = jdbcTemplate.update(ADD_POST, new Object[] {
					content,
					createdDate,
					userId
			});
			
			return count;			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return count;
	}

	@Override
	@CachePut(value = "postById", key = "#post_id")
	public int deletePost(int post_id) {

		int count = 0;
		
		try {
			count = jdbcTemplate.update(DELETE_POST, post_id);
			
			LOGGER.info("Deleting post for post_id: " + post_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}
		
		return count;
	}
	
}
