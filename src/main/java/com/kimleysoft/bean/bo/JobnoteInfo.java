package com.kimleysoft.bean.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("jobnoteInfo")
public class JobnoteInfo {
	@XStreamAlias("jobnote")
	private Jobnote jobnote;

	public Jobnote getJobnote() {
		return jobnote;
	}

	public void setJobnote(Jobnote jobnote) {
		this.jobnote = jobnote;
	}

}
