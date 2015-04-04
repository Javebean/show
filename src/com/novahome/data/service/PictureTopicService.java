package com.novahome.data.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novahome.data.dao.AccountDao;
import com.novahome.data.dao.PictureTopicDao;
import com.novahome.data.dao.TextDao;
import com.novahome.data.pojo.PictureTopic;
import com.novahome.data.pojo.Text;
import com.novahome.utils.Ut;

@Service("pictureTopicService")
@Transactional(readOnly = false)
@Repository
public class PictureTopicService {
	
	@Resource(name = "pictureTopicDao")
	private PictureTopicDao ptDao;
	
	public String savePT(PictureTopic pt) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//设置日期格式
		String dateStr = df.format(new Date());
		pt.setUpdatetime(dateStr);
		pt.setStatus(1);
		String id = ptDao.savePT(pt);
		pt.setId(id);
		JSONObject jo = new JSONObject(pt);
		return jo.toString();
	}
	
	public boolean updatePT(PictureTopic pt) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//设置日期格式
		pt.setUpdatetime(df.format(new Date()));
		ptDao.updatePT(pt);
		return true;
	}
	
	public boolean deletePTById(String id, String basePath) {
		ptDao.deletePTById(id);
		//delete picture
		Ut.pt("path:"+basePath);
		
		File file = new File(basePath+File.separator+id+".jpg");
		Ut.pt(basePath+File.separator+id+".jpg");
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	    }
	    file = new File(basePath+File.separator+"s_"+id+".jpg");  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	    }
		return true;
	}
	
	public String getPTsByType(String type) {
		List<PictureTopic> pts = ptDao.getPTsByType(type);
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		for(PictureTopic t: pts) {
			JSONObject j = new JSONObject(t);
			ja.put(j);
		}
		jo.put("data", ja);
		return jo.toString();
	}
	
	public String getPTById(String id) {
		PictureTopic pt = ptDao.getPTById(id);
		JSONObject jo = new JSONObject();
		JSONObject j = new JSONObject(pt);
		
		jo.put("data", j);
		return jo.toString();
	}
}
