package com.novahome.servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ckeImageUploadServlet extends HttpServlet {
	
	  int bg_width = 600; int bg_height = 600; int set_width = 600;//处理压缩成该宽度
	  
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
	public void upLoad(HttpServletRequest request, HttpServletResponse response) {
		try{
		PrintWriter out = response.getWriter();
		
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(5*1024*1024);

		// Parse the request
		List<FileItem> items = upload.parseRequest(request);
		
		// Process the uploaded items
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = iter.next();
		    String uploadPath = this.getServletContext().getRealPath(
					"/resources/ckeImages");
		    String uploadFileName = item.getName();
	        String fileName = UUID.randomUUID().toString() + uploadFileName.substring(uploadFileName.length() - 4);;  //采用UUID的方式随机命名  
	        File toFile = new File(uploadPath, fileName);  
			BufferedImage bg_src = null;
			try {
				bg_src = javax.imageio.ImageIO.read(item.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedImage tag = null;
			
			if(bg_src != null){//上传的文件是图片的时候
				int real_width = bg_src.getWidth();//图片的真实宽度
				int real_height = bg_src.getHeight();//图片的真实高度
				if(real_width > set_width){//当图片高度超过该限制后才压缩图片
					System.out.println("图片像素过大，进行压缩...");
					double compute_height = (double)set_width/real_width*real_height;//等比例计算出来的图片高度
					bg_width = set_width;
					bg_height = (int)compute_height; 
					tag = new BufferedImage(bg_width, bg_height, BufferedImage.TYPE_INT_RGB);
					Graphics2D g2d = tag.createGraphics();
					g2d.drawImage(bg_src, 0, 0, bg_width, bg_height, null);
					g2d.dispose(); 
					FileOutputStream output = new FileOutputStream(toFile); 
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output); 
					encoder.encode(tag); 
					output.close(); 
					
				}
				else{ 
		   
					item.write(toFile);
				}
			}
		    
		    //设置返回“图像”选项卡  
	        String callback = request.getParameter("CKEditorFuncNum");    
	        out.println("<script type=\"text/javascript\">");    
	        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "resources/ckeImages/" + fileName + "','')");    
	        out.println("</script>");  
		}
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
		//处理压缩图片
				
        //对文件进行校验  
        /*if(upload==null || uploadContentType==null || uploadFileName==null){  
            out.print("<font color=\"red\" size=\"2\">*请选择上传文件</font>");  
        }  
          
        if ((uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg"))  
                && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".jpg")) {  
            //IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg  
        }else if(uploadContentType.equals("image/png") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".png")){  
              
        }else if(uploadContentType.equals("image/gif") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".gif")){  
              
        }else if(uploadContentType.equals("image/bmp") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".bmp")){  
              
        }else{  
            out.print("<font color=\"red\" size=\"2\">*文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）</font>");  
        }  
          
        if(upload.length() > 600*1024){  
            out.print("<font color=\"red\" size=\"2\">*文件大小不得大于600k</font>");  
        }  */
          
          
          
      
	}
}
