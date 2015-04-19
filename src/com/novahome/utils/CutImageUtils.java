package com.novahome.utils;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
/**
 * 图片切割工具类
 * 
 * @author haizhe
 * @version April 2015
 */
public class CutImageUtils {
	/**
	 * 切割图片
	 * 
	 * @param sourceFile
	 *            源文件
	 * @param targetDir
	 *            存储目录
	 * @param width
	 *            切片宽度
	 * @param height
	 *            切片高度
	 * @return
	 * @throws Exception
	 */
	public static String cut(File sourceFile, String targetDir, int originX, int originY,int width, int height)
			throws Exception {
		BufferedImage source = ImageIO.read(sourceFile);
		BufferedImage image = null;
		
		image = source.getSubimage(originX, originY, width,
				height);
		
			String fileName = null;
			File file = new File(targetDir);
			if (!file.exists()) { // 存储目录不存在，则创建目录
				file.mkdirs();
			}
			String picName = UUID.randomUUID() + ".jpg";
			fileName = targetDir + "/" + picName;
			file = new File(fileName);
			ImageIO.write(image, "JPEG", file);	
			sourceFile.delete();
			return picName;
	}
		
	public static void main(String[] args)
	{
		try {
			CutImageUtils.cut(new File("C:\\logs\\01.jpg"), "C:\\logs", 1, 1, 1000, 650);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
