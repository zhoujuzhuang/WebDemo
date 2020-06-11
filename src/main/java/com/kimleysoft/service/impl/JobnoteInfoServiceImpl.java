package com.kimleysoft.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kimleysoft.bean.bo.Cardinfo;
import com.kimleysoft.bean.bo.JPG;
import com.kimleysoft.bean.bo.Promise;
import com.kimleysoft.bean.vo.FolderStyle;
import com.kimleysoft.bean.vo.FoldingInfo;
import com.kimleysoft.config.SystemConfig;
import com.kimleysoft.service.JobnoteInfoService;
import com.kimleysoft.util.FolderMap;
import com.kimleysoft.util.ImageUtil;
import com.kimleysoft.util.XmlTobean;

@Service
@EnableConfigurationProperties(SystemConfig.class)
public class JobnoteInfoServiceImpl implements JobnoteInfoService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private SystemConfig systemConfig;
	
	@Override
	public FoldingInfo getFoldingInfo(String jobno) throws Exception {
		String responseEntity = restTemplate.getForObject(systemConfig.getInterfaceJobnoteInfo()+jobno, String.class);
		Promise promise = XmlTobean.toBean(Promise.class, responseEntity);
		List<Cardinfo> cardinfoList = promise.getJobnoteInfo().getJobnote().getCardinfos().getCardinfo();
		Collections.sort(cardinfoList);//根据款数升序排序
		FoldingInfo foldingInfo = new FoldingInfo();
		List<FolderStyle> folderStyles = new ArrayList<FolderStyle>();
		
		for (Cardinfo cardinfo : cardinfoList) {
			
			JPG fJpg = cardinfo.getFile().getFileinfo().get(0).getJpg();
			JPG bJpg = cardinfo.getFile().getFileinfo().get(1).getJpg();
			
			FolderStyle folderStyle = new FolderStyle();
			
			folderStyle.setChuxuePadding(cardinfo.getChuxuePadding());
			folderStyle.setWidth(cardinfo.getWidth());
			folderStyle.setHeight(cardinfo.getHeight());
			folderStyle.setFoldingWorking(FolderMap.getFoldingWorking(cardinfo.getProcessstr()));
			String folderFilePath = systemConfig.getFolderFilePath();
			String fLasttime = fJpg.getLasttime();
			String[] yearAndDay = fLasttime.split(" ")[0].split("-");
			foldingInfo.setAddtime(yearAndDay[0]+yearAndDay[1]);
			String savePath = folderFilePath + foldingInfo.getAddtime() + 
					File.separator + jobno.substring(0, 4) + File.separator + jobno.substring(4);
			File fimg = new File(savePath + File.separator + fJpg.getFilename());
			File bimg = new File(savePath + File.separator + bJpg.getFilename());
			if(!fimg.exists()) {
				ImageUtil.downloadFile(fJpg.getDownloadurl(), savePath,fJpg.getFilename());
			}
			folderStyle.setFimage(savePath + File.separator + fJpg.getFilename());
			if(!bimg.exists()) {
				ImageUtil.downloadFile(bJpg.getDownloadurl(), savePath,bJpg.getFilename());
			}
			folderStyle.setBimage(savePath + File.separator + bJpg.getFilename());
			folderStyles.add(folderStyle);
		}
		foldingInfo.setJobnote(jobno);
		foldingInfo.setFolderStyles(folderStyles);
		foldingInfo.setStyleNum(folderStyles.size());
		return foldingInfo;
	}

}
