package com.kimleysoft.bean.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Item {
	@XStreamAlias("imgFUrl")
	private String imgFUrl;
	
	@XStreamAlias("imgBUrl")
	private String imgBUrl;

	public String getImgFUrl() {
		return imgFUrl;
	}

	public void setImgFUrl(String imgFUrl) {
		this.imgFUrl = imgFUrl;
	}

	public String getImgBUrl() {
		return imgBUrl;
	}

	public void setImgBUrl(String imgBUrl) {
		this.imgBUrl = imgBUrl;
	}
	
}
