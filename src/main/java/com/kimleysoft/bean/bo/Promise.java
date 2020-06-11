package com.kimleysoft.bean.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("promise")
public class Promise {
	
	@XStreamAlias("jobnoteInfo")
	private JobnoteInfo jobnoteInfo;

	public JobnoteInfo getJobnoteInfo() {
		return jobnoteInfo;
	}

	public void setJobnoteInfo(JobnoteInfo jobnoteInfo) {
		this.jobnoteInfo = jobnoteInfo;
	}
	
}
