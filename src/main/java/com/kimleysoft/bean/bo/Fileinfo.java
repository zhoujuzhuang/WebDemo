package com.kimleysoft.bean.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("fileinfo")
public class Fileinfo {

	@XStreamAlias("jpg")
	private JPG jpg;

	public JPG getJpg() {
		return jpg;
	}

	public void setJpg(JPG jpg) {
		this.jpg = jpg;
	}
}
