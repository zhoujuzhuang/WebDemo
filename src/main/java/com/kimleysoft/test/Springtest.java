package com.kimleysoft.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.kimleysoft.bean.bo.Promise;
import com.kimleysoft.util.XmlTobean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springtest {

	@Autowired
	private RestTemplate restTemplate;

	 @Test
	public void httpGet() {
		String forObject = restTemplate.getForObject("http://192.168.41.161:8188/newgzgd/getJobnotes.action?type=test&jobno=5735605", String.class);
		try {
			Promise bean = XmlTobean.toBean(Promise.class, forObject);
			System.out.println(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void getFile() {
//		String filepath = "\\\\192.168.41.161\\xml\\newgzgd\\epfile\\clientfile/201903\\5735\\605\\jpg\\5735605-1f.jpg";
//		File file = new File(filepath);
//		try {
//			BufferedImage read = ImageIO.read(file);
//			System.out.println(read);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Resource> httpEntity = new HttpEntity<Resource>(headers);
		ResponseEntity<byte[]> response = restTemplate.exchange(
				"http://192.168.41.60:8080/FoldServer/upload/file",
				HttpMethod.GET, httpEntity, byte[].class);
		try {
			File file = File.createTempFile(response.getHeaders().getContentDisposition().getFilename(),
					"." + response.getHeaders().getContentType().getSubtype());
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(response.getBody());
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	@Test
	public void downloadFile() throws Exception {
		URL url = new URL("http://192.168.41.161:8188/newzdxz/get.html?file=%5C%5C192.168.41.161%5Cxml%5Cnewgzgd%5Cepfile%5Cclientfile%2F201903%5C5735%5C605%5Cjpg%5C5735605-1f.jpg");
		HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
		urlCon.setConnectTimeout(5000);
		urlCon.setReadTimeout(5000);
		int code = urlCon.getResponseCode();
		if (code != HttpURLConnection.HTTP_OK) {
			throw new Exception("文件读取失败");
		}
		// 读文件流
		DataInputStream in = new DataInputStream(urlCon.getInputStream());
		DataOutputStream out = new DataOutputStream(new FileOutputStream("d:\\123.jpg"));
		byte[] buffer = new byte[1024];
		int count = 0;
		while ((count = in.read(buffer)) > 0) {
			out.write(buffer, 0, count);
		}
		try {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
