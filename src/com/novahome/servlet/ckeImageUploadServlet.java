package com.novahome.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ckeImageUploadServlet extends HttpServlet {
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
		    item.write(toFile);
		    
		    //设置返回“图像”选项卡  
	        String callback = request.getParameter("CKEditorFuncNum");    
	        out.println("<script type=\"text/javascript\">");    
	        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "resources/ckeImages/" + fileName + "','')");    
	        out.println("</script>");  
		}
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
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
