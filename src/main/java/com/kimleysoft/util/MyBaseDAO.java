package com.kimleysoft.util;

import com.kim.api.Constants;
import com.kim.api.DefaultKimClient;
import com.kim.api.KimClient;

public class MyBaseDAO {
	protected String lankey;
	protected String sessionkey;
	protected KimClient client;
	protected String ip;

	public MyBaseDAO(String lankey, String sessionkey, String ip) {
		this.lankey = lankey;
		this.ip = ip;
		this.sessionkey = sessionkey;
		client = new DefaultKimClient(Constants.SERVER_URL, Constants.APPKEY, Constants.APPSECRET, lankey, ip, Constants.FORMAT_JSON, 360000, 360000);
	}
	
	public MyBaseDAO(String lankey, String sessionkey, String ip, String serverUrl) {
		this.lankey = lankey;
		this.ip = ip;
		this.sessionkey = sessionkey;
		client = new DefaultKimClient(serverUrl, Constants.APPKEY, Constants.APPSECRET, lankey, ip);
	}
}
