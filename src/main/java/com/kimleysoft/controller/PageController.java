package com.kimleysoft.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kimleysoft.bean.vo.FoldingInfo;
import com.kimleysoft.config.SystemConfig;
import com.kimleysoft.exception.APIException;
import com.kimleysoft.exception.ExceptionEnum;
import com.kimleysoft.service.DealImageService;
import com.kimleysoft.service.JobnoteInfoService;
import com.kimleysoft.service.SaveFoldService;
import com.kimleysoft.util.FolderJsonUtil;

@Controller
@EnableConfigurationProperties(SystemConfig.class)
public class PageController {

	@Autowired
	private DealImageService dealImageService;
	@Autowired
	private JobnoteInfoService jobnoteInfoService;
	@Autowired
	private FolderJsonUtil folderJsonUtil;
	@Autowired
	private SaveFoldService saveFoldService;
	@Autowired
	private SystemConfig systemConfig;

	@GetMapping("draw")
	public String draw(HttpServletRequest request, FoldingInfo foldingInfo, Model model) throws Exception {
		if (StringUtils.isNotBlank(foldingInfo.getJobnote())) {
			/**
			 * 1.根据工单号获取折工信息 2.裁切图片
			 */
			foldingInfo = jobnoteInfoService.getFoldingInfo(foldingInfo.getJobnote());
			if (foldingInfo != null) {
				dealImageService.cutImage(request, foldingInfo); // 裁切图片
				model.addAttribute("foldingInfoObj", foldingInfo);
				return foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).getFoldingWorking();
			}
		}
		throw new APIException(ExceptionEnum.JOBNO_NOT_FOUND);
	}

	@RequestMapping("review")
	public String review(HttpServletRequest request, FoldingInfo foldingInfo, Model model) throws Exception {
		if(foldingInfo != null) {
			dealImageService.rotateImage(request, foldingInfo); // 旋转图片
			model.addAttribute("foldingInfoObj", foldingInfo);
			return foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).getReviewAddress();
		}
		throw new APIException(ExceptionEnum.ILLEGAL_ACCESS);
	}
	
	@RequestMapping("changeStyle")
	public String changeStyle(FoldingInfo foldingInfo, Model model) throws Exception {
		if(foldingInfo != null) {
			String filepath = systemConfig.getFolderFilePath() + foldingInfo.getAddtime() + File.separator +
					foldingInfo.getJobnote().substring(0, 4) + File.separator + foldingInfo.getJobnote().substring(4) + 
					File.separator + foldingInfo.getJobnote() + ".json";
			FoldingInfo foldInfo = saveFoldService.getFoldInfo(filepath);
			foldInfo.setStyleIndex(foldingInfo.getStyleIndex());
			model.addAttribute("foldingInfoObj", foldInfo);
			return foldingInfo.getFolderStyles().get(foldInfo.getStyleIndex()).getReviewAddress();
		}
		throw new APIException(ExceptionEnum.ILLEGAL_ACCESS);
	}

	@GetMapping("reviewDirect")
	public String reviewDirect(FoldingInfo foldingInfo, Model model) throws Exception {

		if (foldingInfo.getJobnote() != null) {
			foldingInfo = folderJsonUtil.getFolderInfoByjobno(foldingInfo.getJobnote(),foldingInfo.getStyleIndex());
			if (foldingInfo == null) {
				throw new APIException(ExceptionEnum.JOBNO_NOT_FOUND);
			}
			model.addAttribute("foldingInfoObj", foldingInfo);
			return foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).getReviewAddress() == null
					|| "".equals(foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).getReviewAddress())
							? "wuzhegong"
							: foldingInfo.getFolderStyles().get(foldingInfo.getStyleIndex()).getReviewAddress();
		}
		throw new APIException(ExceptionEnum.JOBNO_NOT_FOUND);
	}
}
