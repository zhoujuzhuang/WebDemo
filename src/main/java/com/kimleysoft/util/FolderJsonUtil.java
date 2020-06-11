package com.kimleysoft.util;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.kim.api.domain.Jobnote;
import com.kim.api.jsonobj.OrdersproofjobnoterelObj;
import com.kimleysoft.bean.bo.Items;
import com.kimleysoft.bean.bo.Promise;
import com.kimleysoft.bean.vo.FolderStyle;
import com.kimleysoft.bean.vo.FoldingInfo;
import com.kimleysoft.config.SystemConfig;
import com.kimleysoft.service.JobnoteInfoService;
import com.kimleysoft.service.impl.PreviewService;

@Component
@EnableConfigurationProperties(SystemConfig.class)
public class FolderJsonUtil {
	
	@Autowired
	private SystemConfig systemConfig;
	@Autowired
	private JobnoteInfoService jobnoteInfoService;
	@Autowired
	private PreviewService previewService;
	
	public FoldingInfo getFolderInfoByjobno(String jobno,Integer styleIndex) throws Exception {
		FoldingInfo foldingInfo = jobnoteInfoService.getFoldingInfo(jobno);//查接口,获取时间
		String filepath = systemConfig.getFolderFilePath() + foldingInfo.getAddtime() + File.separator 
				+jobno.substring(0, 4) + File.separator + jobno.substring(4) + File.separator + jobno + ".json";
		File file = new File(filepath);
		foldingInfo = null;
		if(file.exists()) {
			String folderInfoString = FileUtils.readFileToString(new File(filepath), "UTF-8");
			foldingInfo = JSONObject.parseObject(folderInfoString, FoldingInfo.class);
		}else {
			//
			foldingInfo = new FoldingInfo();
			List<Jobnote> jobnotes = previewService.getJobnoteList("0550478");
			List<OrdersproofjobnoterelObj> listProof = previewService.getOrdersproofjobnoterel(null,"0550478",null);//拿到打稿備註
			if(listProof!=null && listProof.size()>0){
				int i=0;
				for (OrdersproofjobnoterelObj oj : listProof) {
					//打稿未完成   未刪除的 備註任務
					if(oj.getDeleteflag()!=null && oj.getDeleteflag()!=1
							&& oj.getDagaofinishflag()==0 && oj.getDagaofinishflag()!=null && oj.getProofremark()!=null){
						foldingInfo.setProofRemark(oj.getProofremark()); 
					//如果全部是打稿完成的，就拿最後一條打稿備註
					}else if(oj.getDeleteflag()!=null && oj.getDeleteflag()!=1
							&& oj.getDagaofinishflag()==1 && oj.getDagaofinishflag()!=null && oj.getProofremark()!=null){
						i++;
						if(i==listProof.size()){
							foldingInfo.setProofRemark(oj.getProofremark()); 
						}
					}
				}
			}else {
				foldingInfo.setProofRemark("hehe"); 
			}
			
			if(jobnotes!=null && jobnotes.size()>0){
				String pgid = jobnotes.get(0).getProductid();
				if(pgid!=null){
					if("1478336471180324".equals(pgid) || "1481251617481188".equals(pgid) || "1480925892532847".equals(pgid)){//個人掛曆,掛曆,月曆

					}else{
						if("1480925856050844".equals(pgid)){//Photobook

						}/*else if(new File(path+File.separator+"config.xml").exists()){
							書本類
							String path1 = path+File.separator+"config.xml";
							Map<String,Object> map1 = ReadXml.reader(path1,date,jobnoStr,null);
							if(map1!=null){
								if(map1!=null && map1.get("typeFlag")!=null && "emain.swf".equals(map1.get("typeFlag"))){
									typeFlag = 1;
								}
								list = (List<String>) map1.get("list");
//								flag = (Short) map1.get("flag");
								height = (Integer)map1.get("height");
								width = (Integer)map1.get("width");
								return "book";
							}else{
								return "error";
							}
						}*/else{
							//卡片,單張類
							String dirpath = systemConfig.getJobnoteFilePath()+jobno.substring(0, 4) + File.separator + jobno.substring(4);
							File dir = new File(dirpath);
							File[] kuanshu=dir.listFiles(new FileFilter() {
								@Override
								public boolean accept(File pathname) {
									if(pathname.isDirectory() && !"minipic".equals(pathname.getName())) {
										try {
											Integer.parseInt(pathname.getName());
											return true;
										}catch(Exception ex) {
											ex.printStackTrace();
										}
									}
									return false;
								}
							});
							if(kuanshu!=null && kuanshu.length>0) {
								foldingInfo.setStyleNum(kuanshu.length);
							}
							filepath = systemConfig.getJobnoteFilePath()
									+jobno.substring(0, 4) + File.separator + jobno.substring(4) + File.separator + 
									(styleIndex+1) + File.separator + "info.xml";
							File xml = new File(filepath);
							if(xml.exists()) {
								
								String folderInfoString = FileUtils.readFileToString(new File(filepath), "UTF-8");
								Items items = XmlTobean.toBean(Items.class, folderInfoString);
								FolderStyle folderStyle = new FolderStyle();
								List<FolderStyle> folderStyles = new ArrayList<FolderStyle>();
								String fimgurl = ("reviewimg"+File.separator+jobno.substring(0, 4) + File.separator + jobno.substring(4) + File.separator +
										(foldingInfo.getStyleIndex()+1) + File.separator + items.getItem().getImgFUrl()).replaceAll("\\\\", "/");
								folderStyle.setFimage(fimgurl);
								//foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).
								String bimgurl = ("reviewimg"+File.separator+jobno.substring(0, 4) + File.separator + jobno.substring(4) + File.separator +
										(foldingInfo.getStyleIndex()+1) + File.separator + items.getItem().getImgBUrl()).replaceAll("\\\\", "/");
								folderStyle.setBimage(bimgurl);
								folderStyle.setHeight(items.getInfo().getItemHeight());
								folderStyle.setWidth(items.getInfo().getItemWidth());
								folderStyles.add(folderStyle);
								foldingInfo.setFolderStyles(folderStyles);
								foldingInfo.setJobnote(jobno);
							}else {
								foldingInfo = null;
							}
						}
						
					}
				}
			}
		}
		return foldingInfo;
	}
}
