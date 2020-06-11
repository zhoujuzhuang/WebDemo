package com.kimleysoft.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kimleysoft.bean.vo.FolderStyle;
import com.kimleysoft.bean.vo.FoldingInfo;
import com.kimleysoft.service.DealImageService;
import com.kimleysoft.util.ImageUtil;

@Service
public class DealImageServiceImpl implements DealImageService {

	@Override
	public void cutImage(HttpServletRequest request,FoldingInfo foldingInfo) throws Exception {
		if(!ObjectUtils.isEmpty(foldingInfo.getFolderStyles())) {
			for(int i = 0; i < foldingInfo.getFolderStyles().size(); i++) {
				String cutfImage = ImageUtil.cutImage(request,foldingInfo.getFolderStyles().get(i).getFimage());
				foldingInfo.getFolderStyles().get(i).setFimage(cutfImage);
				String cutbImage = ImageUtil.cutImage(request,foldingInfo.getFolderStyles().get(i).getBimage());
				foldingInfo.getFolderStyles().get(i).setBimage(cutbImage);
			}
		}
	}

	@Override
	public void rotateImage(HttpServletRequest request, FoldingInfo foldingInfo) throws Exception {
		if(foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).getAngle() != 0) {
			String fimageUrl = ImageUtil.Rotate(request, foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).getFimage(),
					foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).getAngle());
			foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).setFimage(fimageUrl);
			
			String bimageUrl = ImageUtil.Rotate(request, foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).getBimage(),
					foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).getAngle());
			foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).setBimage(bimageUrl);
		}
		
	}

}
