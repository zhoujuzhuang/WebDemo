package com.kimleysoft.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.kimleysoft.exception.APIException;
import com.kimleysoft.exception.ExceptionEnum;

public class ImageUtil {

	public static String cutImage(HttpServletRequest request, String imageUrl) throws IOException {
		BufferedImage bufImage = ImageIO.read(new File(imageUrl));
		
		BufferedImage subimage = bufImage.getSubimage(0, 0, bufImage.getWidth(), bufImage.getHeight());

		String imageName = imageUrl.substring(imageUrl.lastIndexOf(File.separator) + 1);// 原文件名
		imageName = imageName.substring(0, imageName.lastIndexOf(".")) + "_cut" + "."
				+ imageName.substring(imageName.lastIndexOf(".") + 1);// 重命名
		
		String imagefolder = imageUrl.substring(0, imageUrl.lastIndexOf(File.separator));
		String imgSavePath = imagefolder + File.separator + imageName;
		ImageIO.write(subimage, "jpg", new File(imgSavePath)); // 保存图片.
		String projectUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		String httpImgUrl = projectUrl
				+ imgSavePath.substring(imgSavePath.indexOf(File.separator + "foldimg")).replaceAll("\\\\", "/");
		return httpImgUrl;
	}

	/**
	 * 
	 * @param request
	 * @param imageUrl 图片的网络路径
	 * @param degree   旋转角度
	 * @return 图片旋转后的网络路径
	 * @throws Exception
	 */

	public static String Rotate(HttpServletRequest request, String imageUrl, int degree) throws Exception {
		int swidth = 0; // 旋转后的宽度
		int sheight = 0; // 旋转后的高度
		int x; // 原点横坐标
		int y; // 原点纵坐标

		BufferedImage bi = ImageIO.read(new File(imageUrl)); // 读取该图片
		// 处理角度--确定旋转弧度
		degree = degree % 360;
		if (degree < 0)
			degree = 360 + degree;// 将角度转换到0-360度之间
		double theta = Math.toRadians(degree);// 将角度转为弧度

		// 确定旋转后的宽和高
		if (degree == 180 || degree == 0 || degree == 360) {
			swidth = bi.getWidth();
			sheight = bi.getHeight();
		} else if (degree == 90 || degree == 270) {
			sheight = bi.getWidth();
			swidth = bi.getHeight();
		} else {
			swidth = (int) (Math.sqrt(bi.getWidth() * bi.getWidth() + bi.getHeight() * bi.getHeight()));
			sheight = (int) (Math.sqrt(bi.getWidth() * bi.getWidth() + bi.getHeight() * bi.getHeight()));
		}

		x = (swidth / 2) - (bi.getWidth() / 2);// 确定原点坐标
		y = (sheight / 2) - (bi.getHeight() / 2);

		BufferedImage spinImage = new BufferedImage(swidth, sheight, bi.getType());

		AffineTransform at = new AffineTransform();
		at.rotate(theta, swidth / 2, sheight / 2);// 旋转图象
		at.translate(x, y);
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		spinImage = op.filter(bi, spinImage);
		String imageName = imageUrl.substring(imageUrl.lastIndexOf("uploadimg/") + 10);// 原文件名
		imageName = imageName.substring(0, imageName.lastIndexOf(".")) + "_" + degree + "."
				+ imageName.substring(imageName.lastIndexOf(".") + 1);
		
		String imagefolder = imageUrl.substring(0, imageUrl.lastIndexOf(File.separator));
		String imgSavePath = imagefolder + File.separator + imageName;
		ImageIO.write(spinImage, "jpg", new File(imgSavePath)); // 保存图片.
		String projectUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		String httpImgUrl = projectUrl
				+ imgSavePath.substring(imgSavePath.indexOf(File.separator + "foldimg")).replaceAll("\\\\", "/");
		return httpImgUrl;
	}

	public static void downloadFile(String downloadUrl, String savePath, String fileName) {

		try {
			URL url = new URL(downloadUrl);
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			urlCon.setConnectTimeout(5000);
			urlCon.setReadTimeout(5000);
			// 读文件流
			DataInputStream in = new DataInputStream(urlCon.getInputStream());
			File saveFolderFile = new File(savePath);
			if (!saveFolderFile.exists()) {
				saveFolderFile.mkdirs();
			}
			DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath + File.separator + fileName));
			byte[] buffer = new byte[2048];
			int count = 0;
			while ((count = in.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new APIException(ExceptionEnum.SYSTEM_ERROR);
		}
	}
}
