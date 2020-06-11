package com.kimleysoft.service;

import com.kimleysoft.bean.vo.FoldingInfo;

public interface SaveFoldService {
	public FoldingInfo getFoldInfo(String filepath);
	public FoldingInfo mergeFoldStyle(String filepath,FoldingInfo foldingInfo);
	public FoldingInfo saveFold(FoldingInfo foldingInfo);
}
