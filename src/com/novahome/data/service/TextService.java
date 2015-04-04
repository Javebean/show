package com.novahome.data.service;

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
import com.novahome.data.dao.TextDao;
import com.novahome.data.pojo.Text;
import com.novahome.utils.Ut;

@Service("textService")
@Transactional(readOnly = false)
@Repository
public class TextService {
	
	@Resource(name = "textDao")
	private TextDao textDao;
	
	public String saveText(Text text) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//设置日期格式
		String dateStr = df.format(new Date());
		text.setUpdatetime(dateStr);
		text.setCreatetime(dateStr);
		String id = textDao.saveText(text);
		text.setId(id);
		JSONObject jo = new JSONObject(text);
		return jo.toString();
	}
	
	public boolean updateText(Text text) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//设置日期格式
		text.setUpdatetime(df.format(new Date()));
		textDao.updateText(text);
		return true;
	}
	
	public boolean deleteText(String id) {
		textDao.deleteTextById(id);
		return true;
	}
	
	public String getTextsByAccountId(String id) {
		List<Text> textls = textDao.getTextsByAccountId(id);
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		for(Text t: textls) {
			JSONObject j = new JSONObject(t);
			ja.put(j);
		}
		jo.put("data", ja);
		return jo.toString();
	}
	
	public String getTextOneditByAccountId(String id) {
		Text t = textDao.getTextOneditByAccountId(id);
		Ut.pt(t);
		if(t != null) {
			JSONObject jo = new JSONObject(t);
			return jo.toString();
		} else {
			return "noresult";
		}
	}
}
