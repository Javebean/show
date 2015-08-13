package com.novahome.servlet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageUploadServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		upLoad(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		upLoad(request, response);
	}

	@SuppressWarnings("unchecked")
	public void upLoad(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		String topicId = request.getParameter("topicId");
		System.out.println("topicId:"+topicId);
		if (topicId == null)
			return;
		if(topicId.equals("NEWID")){
			topicId = UUID.randomUUID().toString();
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String name = null;
		String value = null;
		boolean fileFlag = false;
		File tmpFile = null;
		String fName = null;

		FileOutputStream baos = null;
		BufferedOutputStream bos = null;
		Hashtable<String, ArrayList<String>> paramHt = new Hashtable<String, ArrayList<String>>();
		int rtnPos = 0;
		byte[] buffs = new byte[2048];
		// 得到请求类型
		String contentType = request.getContentType();
		int index = contentType.indexOf("boundary=");
		String boundary = "--" + contentType.substring(index + 9);
		String endBoundary = boundary + "--";
		ServletInputStream sis = request.getInputStream();

		// 循环读取文件
		while ((rtnPos = sis.readLine(buffs, 0, buffs.length)) != -1) {
			String strBuff = new String(buffs, 0, rtnPos,"UTF-8");
			if (strBuff.startsWith(boundary)) {

				if (name != null && name.trim().length() > 0) {

					if (fileFlag) {
						bos.flush();
						baos.close();
						bos.close();
						baos = null;
						bos = null;
					} else {
						Object obj = paramHt.get(name);
						ArrayList<String> al = null;
						if (obj == null) {
							al = new ArrayList<String>();
						} else {
							ArrayList<String> arrayList = (ArrayList<String>) obj;
							al = arrayList;
						}
						al.add(value);
						paramHt.put(name, al);
					}
				}
				name = new String();
				value = new String();
				fileFlag = false;
				rtnPos = sis.readLine(buffs, 0, buffs.length);
				if (rtnPos != -1) {

					strBuff = new String(buffs, 0, rtnPos,"UTF-8");
					if (strBuff.toLowerCase().startsWith(
							"content-disposition: form-data; ")) {
						int nIndex = strBuff.toLowerCase().indexOf("name=\"");
						int nLastIndex = strBuff.toLowerCase().indexOf("\"",
								nIndex + 6);
						name = strBuff.substring(nIndex + 6, nLastIndex);
					}
					int fIndex = strBuff.toLowerCase().indexOf("filename=\"");
					if (fIndex != -1) {
						fileFlag = true;
						int fLastIndex = strBuff.toLowerCase().indexOf("\"",
								fIndex + 10);
						fName = strBuff.substring(fIndex + 10, fLastIndex);
						fIndex = fName.lastIndexOf("\\");
						if (fIndex == -1) {
							fIndex = fName.lastIndexOf("/");
							if (fIndex != -1) {
								fName = fName.substring(fIndex + 1);
							}
						} else {
							fName = fName.substring(fIndex + 1);
						}
						if (fName == null || fName.trim().length() == 0) {
							fileFlag = false;
							sis.readLine(buffs, 0, buffs.length);
							sis.readLine(buffs, 0, buffs.length);
							sis.readLine(buffs, 0, buffs.length);
							continue;
						}
					}
					sis.readLine(buffs, 0, buffs.length);
					sis.readLine(buffs, 0, buffs.length);
				}
			} else if (strBuff.startsWith(endBoundary)) {
				if (name != null && name.trim().length() > 0) {
					if (fileFlag) {
						bos.flush();
						baos.close();
						bos.close();
						baos = null;
						bos = null;
					} else {
						Object obj = paramHt.get(name);
						ArrayList<String> al = null;
						if (obj == null) {
							al = new ArrayList<String>();
						} else {
							ArrayList<String> arrayList = (ArrayList<String>) obj;
							al = arrayList;
						}
						al.add(value);

						paramHt.put(name, al);
					}
				}
			} else {
				if (fileFlag) {
					if (baos == null && bos == null) {
						// tmpFile = new File(TMP_DIR + fName);
						tmpFile = File.createTempFile("upload", "image");
						baos = new FileOutputStream(tmpFile);
						bos = new BufferedOutputStream(baos);
					}
					bos.write(buffs, 0, rtnPos);
					baos.flush();
				} else {
					value = value + strBuff;
				}
			}
		}
		
		BufferedImage src = ImageIO.read(tmpFile); // 读入文件
		
		int width = src.getWidth(); // 得到源图宽
		int height = src.getHeight(); // 得到源图长

		double scale = 1;
		if (width > 800 || height > 600) {
			scale = (double) width / (double) 800 > (double) height
					/ (double) 600 ? (double) width / (double) 800
					: (double) height / (double) 600;
			width = (int) (width / scale);
			height = (int) (height / scale);
		}

		String basepath = this.getServletContext().getRealPath(
				"/resources/topicimages/");
		Image image = src.getScaledInstance(width, height,
				Image.SCALE_DEFAULT);
		BufferedImage tag = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		ImageIO.write(tag, "JPEG", new File(basepath + File.separator
				+ topicId + ".jpg"));
		System.out.println("servlet image:" +  topicId);
		
		image = src.getScaledInstance(334, 220, Image.SCALE_DEFAULT);
		tag = new BufferedImage(334, 220, BufferedImage.TYPE_INT_RGB);
		g = tag.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		ImageIO.write(tag, "JPEG", new File(basepath + File.separator
				+ "s_" + topicId + ".jpg"));
		
		tmpFile.delete();
		writer.print(topicId);
		writer.flush();
	}
}
