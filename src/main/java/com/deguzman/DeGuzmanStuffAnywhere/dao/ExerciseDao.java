package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.deguzman.DeGuzmanStuffAnywhere.dto.ExerciseInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.Exercise;

public interface ExerciseDao {

	public List<ExerciseInfoDTO> findAllExerciseInformation();

	public List<ExerciseInfoDTO> findExerciseInformationByUser(long user_id);

	public List<ExerciseInfoDTO> findExerciseInformationByType(int exercise_type_id);

	public ResponseEntity<ExerciseInfoDTO> findExerciseDTOById(int exercise_id);
	
	public ResponseEntity<Exercise> findExerciseById(int exercise_id);

	public int addExerciseInformation(Exercise exercise);

	public int updateExerciseInformation(int exercise_id, Exercise exerciseDetails);

	public int deleteExerciseInformationById(int exercise_id);

	public int deleteAllExercisInformation();
}
