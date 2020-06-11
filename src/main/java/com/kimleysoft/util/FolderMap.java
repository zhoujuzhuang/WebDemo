package com.kimleysoft.util;

import java.util.HashMap;
import java.util.Map;

public class FolderMap {
	public final static Map<String, String> foldermap = new HashMap<String, String>() {
		private static final long serialVersionUID = -3222668809798400728L;
		{
			put("對摺", "duizhe");
			put("對門摺", "duimenzhe");
			put("對摺再對摺", "doubleduizhe");
			put("荷包摺", "hebaozhe");
			put("十字摺", "shizizhe");
			put("風琴摺2條骨", "fengqin2");
			put("風琴摺2條骨再風琴摺4條骨(橫)", "duizhe");
		}
	};
	
	public static String getFoldingWorking(String processstr) {
		String[] processArr = processstr.split(",");
		
		for (String process : processArr) {
			if(foldermap.containsKey(process.trim())) {
				return foldermap.get(process.trim());
			}
		}
		return null;
	}
}
