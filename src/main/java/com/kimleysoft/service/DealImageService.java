package com.kimleysoft.service;

import javax.servlet.http.HttpServletRequest;

import com.kimleysoft.bean.vo.FoldingInfo;

public interface DealImageService {
	public void cutImage(HttpServletRequest request,FoldingInfo foldingInfo) throws Exception;
	public void rotateImage(HttpServletRequest request,FoldingInfo foldingInfo) throws Exception;
}
