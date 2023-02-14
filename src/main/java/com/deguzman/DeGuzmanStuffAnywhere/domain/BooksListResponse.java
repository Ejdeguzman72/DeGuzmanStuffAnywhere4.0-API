package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.model.Books;

public class BooksListResponse {

	public List<Books> list;

	public List<Books> getList() {
		return list;
	}

	public void setList(List<Books> list) {
		this.list = list;
	}
	
	
}
