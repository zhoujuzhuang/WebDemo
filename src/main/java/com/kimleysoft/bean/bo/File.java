package com.kimleysoft.bean.bo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("file")
public class File {
	
	@XStreamImplicit(itemFieldName="fileinfo")
	private List<Fileinfo> fileinfo;

	public List<Fileinfo> getFileinfo() {
		return fileinfo;
	}

	public void setFileinfo(List<Fileinfo> fileinfo) {
		this.fileinfo = fileinfo;
	}
	
}
