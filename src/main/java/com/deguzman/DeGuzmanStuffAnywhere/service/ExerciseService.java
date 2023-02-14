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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.ExerciseDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.ExerciseInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.ExerciseJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.Exercise;

@Service
public class ExerciseService {

	@Autowired
	private ExerciseDaoImpl exerciseDaoImpl;
	
	@Autowired
	private ExerciseJpaDao exerciseDao;
	
	public ExerciseListResponse findAllExerciseInformation() {
		ExerciseListResponse response = new ExerciseListResponse();
		List<ExerciseInfoDTO> list = exerciseDaoImpl.findAllExerciseInformation();
		
		response.setList(list);
		return response;
	}
	
	public ResponseEntity<Map<String, Object>> getAllExercisePagination(@RequestParam(required = false) String exerciseName,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		try {

			List<Exercise> shop = exerciseDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<Exercise> pageBooks = null;

			if (exerciseName == null) {
				pageBooks = exerciseDao.findAll(paging);
			} else {
				// pageBooks = autoShopDao.findByNameContaining(autoShopname, paging);
			}

			shop = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("exercises", shop);
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
	
	public ExerciseListResponse findExerciseInformationByUser(@PathVariable long user_id) {
		ExerciseListResponse response = new ExerciseListResponse();
		List<ExerciseInfoDTO> list = exerciseDaoImpl.findExerciseInformationByUser(user_id);
		
		response.setList(list);
		return response;
	}
	
	public ExerciseListResponse findExerciseInformationByType(int exercise_type_id) {
		ExerciseListResponse response = new ExerciseListResponse();
		List<ExerciseInfoDTO> list = exerciseDaoImpl.findExerciseInformationByType(exercise_type_id);
		
		response.setList(list);
		return response;
	}
	
	public ResponseEntity<ExerciseInfoDTO> findExerciseDTOById(@PathVariable int exercise_id) {
		return exerciseDaoImpl.findExerciseDTOById(exercise_id);
	}
	
	public int addExerciseInformation(com.deguzman.DeGuzmanStuffAnywhere.model.Exercise exercise) {
		return exerciseDaoImpl.addExerciseInformation(exercise);
	}
	
	public int updateExerciseInformation(@PathVariable int exercise_id, @RequestBody com.deguzman.DeGuzmanStuffAnywhere.model.Exercise exerciseDetails) {
		return exerciseDaoImpl.updateExerciseInformation(exercise_id, exerciseDetails);
	}
	
	public int deleteExerciseInformationbyId(int exercise_id) {
		return exerciseDaoImpl.deleteExerciseInformationById(exercise_id);
	}
	
	public int deleteAllExerciseInformation() {
		return exerciseDaoImpl.deleteAllExercisInformation();
	}

	public ResponseEntity<com.deguzman.DeGuzmanStuffAnywhere.model.Exercise> findExerciseById(int exercise_id) {
		return exerciseDaoImpl.findExerciseById(exercise_id);
	}
}
