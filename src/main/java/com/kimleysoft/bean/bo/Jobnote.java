package com.kimleysoft.bean.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("jobnote")
public class Jobnote {

	@XStreamAlias("jobno")
	private String jobno;
	
	@XStreamAlias("cardinfos")
	private Cardinfos cardinfos;
	
	public String getJobno() {
		return jobno;
	}

	public void setJobno(String jobno) {
		this.jobno = jobno;
	}

	public Cardinfos getCardinfos() {
		return cardinfos;
	}

	public void setCardinfos(Cardinfos cardinfos) {
		this.cardinfos = cardinfos;
	}

}
