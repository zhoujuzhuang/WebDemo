package com.kimleysoft.bean.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("items")
public class Items {
	@XStreamAlias("info")
	private Info info;
	
	@XStreamAlias("item")
	private Item item;

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
}
