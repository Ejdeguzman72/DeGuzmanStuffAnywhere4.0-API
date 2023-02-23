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
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseDTOSearchResposne;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ExerciseSearchResposne;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByIntRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByLongRequest;
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
	public ExerciseListResponse getExerciseInformationByUser(@RequestBody @Valid SearchByLongRequest request) {
		ExerciseListResponse response = exerciseInfoService.findExerciseInformationByUser(request);
		return response;
	}

	@GetMapping("/all/exercise-type/{exercise_type_id}")
	@CrossOrigin
	public ExerciseListResponse getExerciseInformationbyType(@RequestBody @Valid SearchByIntRequest request) {
		ExerciseListResponse response = exerciseInfoService.findExerciseInformationByType(request);
		return response;
	}

	@GetMapping("/exercise/{exercise_id}")
	@CrossOrigin
	public ExerciseSearchResposne getExerciseById(@RequestBody @Valid SearchByIntRequest request) {
		ExerciseSearchResposne response = exerciseInfoService.findExerciseById(request);
		return response;
	}
	
	@GetMapping("/exercise-dto/{exercise_id}")
	@CrossOrigin
	public ExerciseDTOSearchResposne getExerciseDTOById(@RequestBody @Valid SearchByIntRequest request) {
		ExerciseDTOSearchResposne response = exerciseInfoService.findExerciseDTOById(request);
		return response;
	}

	@PostMapping("/add-exercise-information")
	@CrossOrigin
	public ExerciseAddUpdateResponse addExerciseInformation(@RequestBody @Valid ExerciseAddUpdateRequest request) {
		ExerciseAddUpdateResponse response = exerciseInfoService.addExerciseInformation(request);
		return response;
	}
	
	@PutMapping("/exercise/{exercise_id}")
	@CrossOrigin
	public ExerciseAddUpdateResponse updateExerciseInformation(@RequestBody @Valid ExerciseAddUpdateRequest request) {
		ExerciseAddUpdateResponse response = exerciseInfoService.updateExerciseInformation(request);
		return response;
	}

	@DeleteMapping("/exercise/{exercise_id}")
	@CrossOrigin
	public int deleteExerciseById(@PathVariable int exercise_id) {
		return exerciseInfoService.deleteExerciseInformationbyId(exercise_id);
	}

	@DeleteMapping("/delete-all-exercises")
	@CrossOrigin
	public DeleteAllResponse deleteAllExercises() {
		DeleteAllResponse response = exerciseInfoService.deleteAllExerciseInformation();
		return response;
	}
}
