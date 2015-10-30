package com.novahome.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.novahome.data.pojo.Visitor;
import com.novahome.data.service.VisitorService;

/**
 * Servlet implementation class DownExcelServlet
 */

public class DownExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  static final String headers[]={"编号","姓名","性别","职务","手机","邮箱","公司","申请时间","证件号码","证件类型","持卡人类型","是否有证件照片","申请状态"};
	
	private VisitorService visitorService ;
	
	@Override
	public void init()
	{
		 ApplicationContext ctx = WebApplicationContextUtils
		    		.getRequiredWebApplicationContext(this.getServletConfig()
		    				.getServletContext());
		 visitorService = (VisitorService) ctx.getBean("visitorService");    		
	}
	
	
	@Override  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String fileName = "证件信息";
			//解决中文名乱码问题
			response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8")+".xls");
		
			/*String hql = "select new Visitor(id, name, sex, position, phone, email, org, applyTime,idNo,idType,type,photo,state) from Visitor";
			List<Visitor> vi = sessionFactory.getCurrentSession().createQuery(hql).list();*/
			List<Visitor> vi = visitorService.getAllVisitors(0, Integer.MAX_VALUE, null);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("people");
			  
			HSSFCellStyle cellStyle = wb.createCellStyle(); 
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中 
			
			HSSFRow rowHeader = sheet.createRow(0);
			for(int i=0;i<headers.length;i++){
				HSSFCell cell= rowHeader.createCell(i);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(headers[i]);
			}
			
			
			for(int i=1;i<=vi.size();i++){
				HSSFRow row = sheet.createRow(i);
				Visitor v = vi.get(i-1);
				String idtype = "";
				String type = "";
				switch (v.getIdType()) {
				case 0:
					idtype = "身份证";
					break;
				case 1:
					idtype = "护照";
					break;
				case 2:
					idtype = "港澳台通行证";
					break;
				case 3:
					idtype = "台胞证";
					break;
				default:
					break;
				}
				
				switch (v.getType()) {
				case 1:
					type="展商";
					break;
				case 2:
					type="观众";
					break;
				case 3:
					type="媒体";
					break;
				default:
					type="观众";
					break;
				}
				String isHavePhoto = "无";
				if(v.getPhoto()!=null&&v.getPhoto().trim()!=""&&v.getPhoto().trim().length()>20){
					isHavePhoto = "有";
				}
				String stateStr = "";
				switch (v.getState()) {
				case 0:
					stateStr = "申请中";
					break;
				case 1:
					stateStr = "已通过";
					break;
				case 2:
					stateStr = "已驳回";
					break;

				default:
					break;
				}
				
				
				String arg[] = {v.getId(),v.getName(),v.getSex(),v.getPosition(),v.getPhone(),v.getEmail(),v.getOrg(),v.getApplyTime()+"",v.getIdNo(),idtype,type,isHavePhoto,stateStr};
				for(int j=0;j<headers.length;j++){
					HSSFCell cell = row.createCell(j);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(arg[j]);
				}
			}
			
			//session.getSessionFactory().close();
			
			//FileOutputStream fileOut = new FileOutputStream("e:\\people.xls");
			//wb.write(fileOut);
			//fileOut.close();
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
			
	}
	@Override  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
