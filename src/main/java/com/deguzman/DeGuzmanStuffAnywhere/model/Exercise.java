package com.deguzman.DeGuzmanStuffAnywhere.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class Exercise implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1101615341293557860L;
	public int exercise_id;
	
	@NotNull(message = "Invalid request, make field is missing/null")
	@Pattern(regexp = "[a-zA-Z]{0,50}", message = "Invalid request, make field can only contain letters, numbers/special characters are not allowed")
	public String exerciseName;
	
	@NotNull(message = "Invalid request, sets field is missing/null")
	@Pattern(regexp = "[0-9]{1,4}", message = "Invalid request, sets field can only contain letters, special characters are not allowed")
	public int sets;
	
	@NotNull(message = "Invalid request, reps field is missing/null")
	@Pattern(regexp = "[0-9]{1,4}", message = "Invalid request, reps field can only contain letters, special characters are not allowed")
	public int reps;
	
	@NotNull(message = "Invalid request, weight field is missing/null")
	@Pattern(regexp = "[0-9]{1,4}", message = "Invalid request, weight field can only contain letters, special characters are not allowed")
	public double weight;
	
	@NotNull(message = "Invalid request, date field is missing/null")
	@Pattern(regexp = "[0-9]{1,4}", message = "Invalid request, date field can only contain letters, special characters are not allowed")
	public String date;
	
	@NotNull(message = "Invalid request, exercise_type_id field is missing/null")
	@Pattern(regexp = "[0-9]{1,4}", message = "Invalid request, exercise_type_id field can only contain letters, special characters are not allowed")
	public int exercise_type_id;
	
	@NotNull(message = "Invalid request, user_id field is missing/null")
	@Pattern(regexp = "[0-9]{1,4}", message = "Invalid request, user_id field can only contain letters, special characters are not allowed")
	public long user_id;
	
	public int getExercise_id() {
		return exercise_id;
	}
	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}
	public String getExerciseName() {
		return exerciseName;
	}
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getExercise_type_id() {
		return exercise_type_id;
	}
	public void setExercise_type_id(int exercise_type_id) {
		this.exercise_type_id = exercise_type_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((exerciseName == null) ? 0 : exerciseName.hashCode());
		result = prime * result + exercise_id;
		result = prime * result + exercise_type_id;
		result = prime * result + reps;
		result = prime * result + sets;
		result = prime * result + (int) (user_id ^ (user_id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Exercise other = (Exercise) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (exerciseName == null) {
			if (other.exerciseName != null)
				return false;
		} else if (!exerciseName.equals(other.exerciseName))
			return false;
		if (exercise_id != other.exercise_id)
			return false;
		if (exercise_type_id != other.exercise_type_id)
			return false;
		if (reps != other.reps)
			return false;
		if (sets != other.sets)
			return false;
		if (user_id != other.user_id)
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Exercise [exercise_id=" + exercise_id + ", exerciseName=" + exerciseName + ", sets=" + sets + ", reps="
				+ reps + ", weight=" + weight + ", date=" + date + ", exercise_type_id=" + exercise_type_id
				+ ", user_id=" + user_id + "]";
	}
	public Exercise(int exercise_id, String exerciseName, int sets, int reps, double weight, String date,
			int exercise_type_id, long user_id) {
		super();
		this.exercise_id = exercise_id;
		this.exerciseName = exerciseName;
		this.sets = sets;
		this.reps = reps;
		this.weight = weight;
		this.date = date;
		this.exercise_type_id = exercise_type_id;
		this.user_id = user_id;
	}
	public Exercise() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
