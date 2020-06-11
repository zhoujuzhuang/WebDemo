package com.kimleysoft.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class SystemConfig {

	private String folderFilePath;
	private String jobnoteFilePath;
	
	private String interfaceJobnoteInfo;

	public String getFolderFilePath() {
		return folderFilePath;
	}

	public void setFolderFilePath(String folderFilePath) {
		this.folderFilePath = folderFilePath;
	}

	public String getJobnoteFilePath() {
		return jobnoteFilePath;
	}

	public void setJobnoteFilePath(String jobnoteFilePath) {
		this.jobnoteFilePath = jobnoteFilePath;
	}

	public String getInterfaceJobnoteInfo() {
		return interfaceJobnoteInfo;
	}

	public void setInterfaceJobnoteInfo(String interfaceJobnoteInfo) {
		this.interfaceJobnoteInfo = interfaceJobnoteInfo;
	}

}
