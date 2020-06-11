package com.kimleysoft.bean.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("cardinfo")
public class Cardinfo implements Comparable<Cardinfo>{

	@XStreamAsAttribute()
	private Integer djk;
	@XStreamAlias("width")
	private Float width;
	@XStreamAlias("height")
	private Float height;
	@XStreamAlias("chuxuePadding")
	private String chuxuePadding;
	@XStreamAlias("processstr")
	private String processstr;
	@XStreamAlias("file")
	private File file;
	
	public Integer getDjk() {
		return djk;
	}
	public void setDjk(Integer djk) {
		this.djk = djk;
	}
	public Float getWidth() {
		return width;
	}
	public void setWidth(Float width) {
		this.width = width;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public String getChuxuePadding() {
		return chuxuePadding;
	}
	public void setChuxuePadding(String chuxuePadding) {
		this.chuxuePadding = chuxuePadding;
	}
	public String getProcessstr() {
		return processstr;
	}
	public void setProcessstr(String processstr) {
		this.processstr = processstr;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	@Override
	public int compareTo(Cardinfo o) {
		return this.djk - o.djk;
	}
	
}
