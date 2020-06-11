package com.kimleysoft.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kimleysoft.bean.vo.FolderStyle;
import com.kimleysoft.bean.vo.FoldingInfo;
import com.kimleysoft.config.SystemConfig;
import com.kimleysoft.exception.APIException;
import com.kimleysoft.exception.ExceptionEnum;
import com.kimleysoft.service.SaveFoldService;

@Service
public class SaveFoldServiceImpl implements SaveFoldService {

	@Autowired
	private SystemConfig systemConfig;

	@Override
	public FoldingInfo getFoldInfo(String filepath) {
		String folderInfoString = "";
		try {
			folderInfoString = FileUtils.readFileToString(new File(filepath),"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		FoldingInfo foldingInfo = JSONObject.parseObject(folderInfoString,FoldingInfo.class);
		return foldingInfo;
	}

	@Override
	public FoldingInfo mergeFoldStyle(String filepath, FoldingInfo foldingInfo) {

		File file = new File(filepath);
		if (file.exists()) {
			FoldingInfo foldingInfoJson = getFoldInfo(filepath);
			FolderStyle folderStyle = foldingInfoJson.getFolderStyles().get(foldingInfo.getStyleIndex());// 取出之前保存的，当前的款数
			folderStyle = foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex());// 替换
			foldingInfoJson.getFolderStyles().set(foldingInfo.getStyleIndex(),folderStyle);
			foldingInfo.setFolderStyles(foldingInfoJson.getFolderStyles());
		} else {// 第一次保存json
			Integer styleNum = foldingInfo.getStyleNum();// 获取所有款数
			if (foldingInfo.getStyleIndex() + 1 < styleNum) {// 当前款数小于总款数,初始化并补全所有款数
				for (int i = 0; i < styleNum - foldingInfo.getStyleIndex() - 1; i++) {
					foldingInfo.getFolderStyles().add(new FolderStyle());
				}
			}
		}

		return foldingInfo;
	}

	@Override
	public FoldingInfo saveFold(FoldingInfo foldingInfo) {
		String filepath = systemConfig.getFolderFilePath()
				+ foldingInfo.getAddtime() + File.separator
				+ foldingInfo.getJobnote().substring(0, 4) + File.separator
				+ foldingInfo.getJobnote().substring(4) + File.separator
				+ foldingInfo.getJobnote() + ".json";

		foldingInfo = mergeFoldStyle(filepath, foldingInfo);

		try {
			FileSystemResource resource = new FileSystemResource(filepath);
			FileWriter fileWriter = (new FileWriter(resource.getFile()));
			fileWriter.write(JSONObject.toJSONString(foldingInfo));
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new APIException(ExceptionEnum.SYSTEM_ERROR);
		}
		return foldingInfo;
	}

}
