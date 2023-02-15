package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.List;
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

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.ExerciseDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseAddRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.ExerciseInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.Exercise;
import com.deguzman.DeGuzmanStuffAnywhere.service.ExerciseService;

@RestController
@RequestMapping("/app/gym-tracker")
@CrossOrigin
public class ExerciseController {
	
	@Autowired
	private ExerciseService exerciseInfoService;

	@GetMapping("/all")
	@CrossOrigin
	public ExerciseListResponse getAllExerciseInformation() {
		ExerciseListResponse response = exerciseInfoService.findAllExerciseInformation();
		return response;
	}
	
	@GetMapping("/all-exercises")
	@CrossOrigin
	public ResponseEntity<Map<String, Object>> getAllExercisePagination(@RequestParam(required = false) String exerciseName,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return exerciseInfoService.getAllExercisePagination(exerciseName, page, size);
	}

	@GetMapping("/all/name/{user_id}")
	@CrossOrigin
	public ExerciseListResponse getExerciseInformationByUser(@PathVariable long user_id) {
		ExerciseListResponse response = exerciseInfoService.findExerciseInformationByUser(user_id);
		return response;
	}

	@GetMapping("/all/exercise-type/{exercise_type_id}")
	@CrossOrigin
	public ExerciseListResponse getExerciseInformationbyType(@PathVariable int exercise_type_id) {
		ExerciseListResponse response = exerciseInfoService.findExerciseInformationByType(exercise_type_id);
		return response;
	}

	@GetMapping("/exercise/{exercise_id}")
	@CrossOrigin
	public ResponseEntity<Exercise> getExerciseById(@PathVariable int exercise_id) {
		return exerciseInfoService.findExerciseById(exercise_id);
	}
	
	@GetMapping("/exercise-dto/{exercise_id}")
	@CrossOrigin
	public ResponseEntity<ExerciseInfoDTO> getExerciseDTOById(@PathVariable int exercise_id) {
		return exerciseInfoService.findExerciseDTOById(exercise_id);
	}

	@PostMapping("/add-exercise-information")
	@CrossOrigin
	public int addExerciseInformation(@RequestBody @Valid ExerciseAddRequest request) {
		return exerciseInfoService.addExerciseInformation(request);
	}
	
	@PutMapping("/exercise/{exercise_id}")
	@CrossOrigin
	public int updateExerciseInformation(@PathVariable int exercise_id, @RequestBody Exercise exerciseDetails) {
		return exerciseInfoService.updateExerciseInformation(exercise_id, exerciseDetails);
	}

	@DeleteMapping("/exercise/{exercise_id}")
	@CrossOrigin
	public int deleteExerciseById(@PathVariable int exercise_id) {
		return exerciseInfoService.deleteExerciseInformationbyId(exercise_id);
	}

	@DeleteMapping("/delete-all-exercises")
	@CrossOrigin
	public int deleteAllExercises() {
		return exerciseInfoService.deleteAllExerciseInformation();
	}
}
