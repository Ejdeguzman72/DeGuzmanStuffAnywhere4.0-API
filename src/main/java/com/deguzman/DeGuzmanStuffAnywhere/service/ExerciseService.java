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

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.ExerciseDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseDTOSearchResposne;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseSearchResposne;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByIntRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByLongRequest;
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

	public ResponseEntity<Map<String, Object>> getAllExercisePagination(
			@RequestParam(required = false) String exerciseName, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
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

	public ExerciseListResponse findExerciseInformationByUser(SearchByLongRequest request) {
		ExerciseListResponse response = new ExerciseListResponse();
		List<ExerciseInfoDTO> list = exerciseDaoImpl.findExerciseInformationByUser(request.getId());

		response.setList(list);
		return response;
	}

	public ExerciseListResponse findExerciseInformationByType(SearchByIntRequest request) {
		ExerciseListResponse response = new ExerciseListResponse();
		List<ExerciseInfoDTO> list = exerciseDaoImpl.findExerciseInformationByType(request.getId());

		response.setList(list);
		return response;
	}

	public ExerciseDTOSearchResposne findExerciseDTOById(SearchByIntRequest request) {
		ExerciseDTOSearchResposne response = new ExerciseDTOSearchResposne();
		ExerciseInfoDTO exercise = exerciseDaoImpl.findExerciseDTOById(request.getId());
		
		response.setExercise(exercise);
		return response;
	}

	public ExerciseAddUpdateResponse addExerciseInformation(ExerciseAddUpdateRequest request) {
		ExerciseAddUpdateResponse response = new ExerciseAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Exercise exercise = null;
		int count = 0;

		count = exerciseDaoImpl.addExerciseInformation(request);

		if (count > 0) {
			exercise.setDate(request.getDate());
			exercise.setExerciseName(request.getExerciseName());
			exercise.setExercise_type_id(request.getExercise_type_id());
			exercise.setReps(request.getReps());
			exercise.setSets(request.getSets());
			exercise.setUser_id(request.getUser_id());
			exercise.setWeight(request.getWeight());

			if (exercise != null) {
				response.setExercise(exercise);
			}
		}

		return response;
	}

	public ExerciseAddUpdateResponse updateExerciseInformation(ExerciseAddUpdateRequest request) {
		ExerciseAddUpdateResponse response = new ExerciseAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Exercise exercise = exerciseDaoImpl.findExerciseById(request.getExercise_id());
		int count = 0;

		count = exerciseDaoImpl.updateExerciseInformation(request.getExercise_id(), request);
		if (count > 0) {
			exercise.setDate(request.getDate());
			exercise.setExercise_type_id(request.getExercise_type_id());
			exercise.setExerciseName(request.getExerciseName());
			exercise.setReps(request.getReps());
			exercise.setSets(request.getSets());
			exercise.setUser_id(request.getUser_id());
			exercise.setWeight(request.getWeight());
			if (exercise != null) {
				response.setExercise(exercise);
			}
		}

		return response;
	}

	public int deleteExerciseInformationbyId(int exercise_id) {
		return exerciseDaoImpl.deleteExerciseInformationById(exercise_id);
	}

	public DeleteAllResponse deleteAllExerciseInformation() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = exerciseDaoImpl.deleteAllExercisInformation();

		response.setCount(count);

		return response;
	}

	public ExerciseSearchResposne findExerciseById(SearchByIntRequest request) {
		ExerciseSearchResposne response = new ExerciseSearchResposne();
		com.deguzman.DeGuzmanStuffAnywhere.model.Exercise exercise = exerciseDaoImpl.findExerciseById(request.getId());
		
		response.setExercise(exercise);
		return response;
	}
}
