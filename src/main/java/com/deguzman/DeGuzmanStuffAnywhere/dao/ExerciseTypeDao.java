package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.deguzman.DeGuzmanStuffAnywhere.model.ExerciseType;

public interface ExerciseTypeDao {

	public List<ExerciseType> findAllExerciseTypeInformation();

	public ResponseEntity<ExerciseType> findExerciseTypeInformationById(int exercise_type_id);

}
