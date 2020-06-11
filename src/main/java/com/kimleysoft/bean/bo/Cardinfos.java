package com.kimleysoft.bean.bo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("cardinfos")
public class Cardinfos {
	@XStreamImplicit(itemFieldName="cardinfo")
	private List<Cardinfo> cardinfo;

	public List<Cardinfo> getCardinfo() {
		return cardinfo;
	}

	public void setCardinfo(List<Cardinfo> cardinfo) {
		this.cardinfo = cardinfo;
	}
	
}
