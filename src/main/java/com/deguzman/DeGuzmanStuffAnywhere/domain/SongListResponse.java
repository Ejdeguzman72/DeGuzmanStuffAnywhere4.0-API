package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.model.Song;

public class SongListResponse {

	List<Song> list;

	public List<Song> getList() {
		return list;
	}

	public void setList(List<Song> list) {
		this.list = list;
	}
}
