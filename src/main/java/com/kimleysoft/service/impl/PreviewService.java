package com.kimleysoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kim.api.ApiException;
import com.kim.api.Constants;
import com.kim.api.domain.Jobnote;
import com.kim.api.internal.util.IpUtil;
import com.kim.api.jsonobj.OrdersproofjobnoterelObj;
import com.kim.api.request.JobnotesGetRequest;
import com.kim.api.request.OrderProofJobnoteRelsRequest;
import com.kim.api.response.JobnotesGetResponse;
import com.kim.api.response.OrdersProofJobnoteRelsResponse;
import com.kimleysoft.util.MyBaseDAO;

@Service
public class PreviewService extends MyBaseDAO{

	public PreviewService() {
		super(Constants.SERVER_LANKEY, Constants.SERVER_SESSION, IpUtil.getRealIp());
	}
	
	public List<Jobnote> getJobnoteList(String jobno) throws ApiException{
		JobnotesGetRequest reqGet = new JobnotesGetRequest();
		reqGet.setJobno(jobno);
		JobnotesGetResponse resp = client.execute(reqGet, sessionkey);
		List<Jobnote> list = new ArrayList<Jobnote>();
		if (resp.isSuccess()) {
			list = resp.getJobnoteList();
			return list;
		} else {
			throw new ApiException(resp.getErrorCode(), resp.getMsg());
		}
	}
	
	/**
	 * @方法說明 获取所有打稿子任务
	 * @author mjun
	 * @創建時間 2017/9/8下午8:30:39
	 * @項目名稱 e-print
	 * @方法名稱 getOrdersproofjobnoterel
	 * @所屬類  ProofServiceImpl.java 
	 * @路徑 com.kimleysoft.order.service 
	 * @param poofid 打稿id
	 * @param jobno 工单号
	 * @param customercode 客户编号
	 * @return
	 * @throws Exception
	 */
	public List<OrdersproofjobnoterelObj> getOrdersproofjobnoterel(String poofid,String jobno,String customercode) throws Exception{
		OrderProofJobnoteRelsRequest req = new OrderProofJobnoteRelsRequest();
		if(StringUtils.isNoneBlank(poofid))req.setProofid(poofid);
		if(StringUtils.isNoneBlank(jobno))req.setJobno(jobno);
		if(StringUtils.isNoneBlank(customercode))req.setCustomercode(customercode);
		OrdersProofJobnoteRelsResponse resp = client.execute(req, sessionkey);
		if (resp.isSuccess()) { 
			return resp.getOrdersproofjobnoterelObjList();
		}
		return null;
	}

}
