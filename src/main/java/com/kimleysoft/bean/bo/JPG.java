package com.kimleysoft.bean.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("jpg")
public class JPG {
	@XStreamAlias("filename")
	private String filename;
	@XStreamAlias("downloadurl")
	private String downloadurl;
	@XStreamAlias("lasttime")
	private String lasttime;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDownloadurl() {
		return downloadurl;
	}
	public void setDownloadurl(String downloadurl) {
		this.downloadurl = downloadurl;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
}
