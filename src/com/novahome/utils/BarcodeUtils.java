package com.novahome.utils;

import java.io.File;
import java.util.UUID;

import org.apache.log4j.Logger;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.novahome.commonservice.Constants;

public class BarcodeUtils {

	private static final Logger logger = Logger.getLogger(BarcodeUtils.class);
	public static String createBarcode(String content)
	{
		int width = 100;
		int height = 100;
		String format = "png";
		String picName ;
		try {
			String nowpath = System.getProperty("user.dir");   
			String tempdir = nowpath.replace("bin", "webapps");
			tempdir+="\\"+ ConfigUtils.getPrj(); 
			String basePath = tempdir + Constants.BARCODE_MID_STR;
			picName = UUID.randomUUID() + ".png";
			String fullPath = basePath + picName;
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height);
			File outputFile = new File(fullPath);
			logger.info("barcode filename:" + fullPath);
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return picName;
	}
	
	public static String createBadgeBarcode(String content)
	{
		int width = 70;
		int height = 70;
		String format = "png";
		String picName ;
		try {
			String nowpath = System.getProperty("user.dir");
			String tempdir = nowpath.replace("bin", "webapps");
			tempdir+="\\"+ ConfigUtils.getPrj(); 
			String basePath = tempdir + Constants.BARCODE_MID_STR;
			picName = UUID.randomUUID() + ".png";
			String fullPath = basePath + picName;
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height);
			
			File outputFile = new File(fullPath);
			logger.info("barcode filename:" + fullPath);
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return picName;
	}
}
