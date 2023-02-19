package com.deguzman.DeGuzmanStuffAnywhere.domain;

public class DeleteAllResponse {

	public int count;
	public String result = String.valueOf(count) + " Records have been deleted";
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
