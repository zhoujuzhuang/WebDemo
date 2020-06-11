package com.kimleysoft.bean.vo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class FoldingInfo {

	private List<FolderStyle> folderStyles;
	private Integer styleNum;
	@JSONField(serialize = false)
	private Integer styleIndex = 0;
	private String jobnote;
	private String addtime;
	private String proofRemark;
	private String step;
	public List<FolderStyle> getFolderStyles() {
		return folderStyles;
	}
	public void setFolderStyles(List<FolderStyle> folderStyles) {
		this.folderStyles = folderStyles;
	}
	public Integer getStyleNum() {
		return styleNum;
	}
	public void setStyleNum(Integer styleNum) {
		this.styleNum = styleNum;
	}
	public Integer getStyleIndex() {
		return styleIndex;
	}
	public void setStyleIndex(Integer styleIndex) {
		this.styleIndex = styleIndex;
	}
	public String getJobnote() {
		return jobnote;
	}
	public void setJobnote(String jobnote) {
		this.jobnote = jobnote;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getProofRemark() {
		return proofRemark;
	}
	public void setProofRemark(String proofRemark) {
		this.proofRemark = proofRemark;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	
}
