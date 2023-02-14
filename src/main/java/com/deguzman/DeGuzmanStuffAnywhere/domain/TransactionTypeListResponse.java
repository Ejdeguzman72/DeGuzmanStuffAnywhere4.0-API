package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.model.TransactionType;

public class TransactionTypeListResponse {

	List<TransactionType> list;

	public List<TransactionType> getList() {
		return list;
	}

	public void setList(List<TransactionType> list) {
		this.list = list;
	}
}
