package com.deguzman.DeGuzmanStuffAnywhere.model;

import java.io.Serializable;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class ExerciseType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118064847455021642L;
	public int exercise_type_id;
	public String exercise_type_name;

	public int getExercise_type_id() {
		return exercise_type_id;
	}

	public void setExercise_type_id(int exercise_type_id) {
		this.exercise_type_id = exercise_type_id;
	}

	public String getExercise_type_name() {
		return exercise_type_name;
	}

	public void setExercise_type_name(String exercise_type_name) {
		this.exercise_type_name = exercise_type_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + exercise_type_id;
		result = prime * result + ((exercise_type_name == null) ? 0 : exercise_type_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExerciseType other = (ExerciseType) obj;
		if (exercise_type_id != other.exercise_type_id)
			return false;
		if (exercise_type_name == null) {
			if (other.exercise_type_name != null)
				return false;
		} else if (!exercise_type_name.equals(other.exercise_type_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExerciseType [exercise_type_id=" + exercise_type_id + ", exercise_type_name=" + exercise_type_name
				+ "]";
	}

	public ExerciseType(int exercise_type_id, String exercise_type_name) {
		super();
		this.exercise_type_id = exercise_type_id;
		this.exercise_type_name = exercise_type_name;
	}

	public ExerciseType() {
		super();
		// TODO Auto-generated constructor stub
	}

}
