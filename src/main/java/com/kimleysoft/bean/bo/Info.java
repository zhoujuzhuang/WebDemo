package com.kimleysoft.bean.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("info")
public class Info {
	@XStreamAlias("itemWidth")
	private Float itemWidth;
	
	@XStreamAlias("itemHeight")
	private Float itemHeight;

	public Float getItemWidth() {
		return itemWidth;
	}

	public void setItemWidth(Float itemWidth) {
		this.itemWidth = itemWidth;
	}

	public Float getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(Float itemHeight) {
		this.itemHeight = itemHeight;
	}
	
}
