package com.novahome.commonservice;

import java.util.ArrayList;
import java.util.List;


public class Constants {

	//public static final String PRJ_NAME="show";        //  你项目的名称
	public static final String BARCODE_MID_STR = "/resources/barcodeimages/";

	public static final String SESSION_ID = "_userId";
	public static final String SESSION_NAME = "_userName";
	public static final String SESSION_OFFICIAL_NAME = "_officialName";
	public static final String USER_CLASS = "_userClass";

	public static final String MENU_ACCESS = "_menuAccess";
	/**
	 * haizhe add here, for audience,exhibitors and staff logging
	 */
	public static final String SESSION_SHOW_ID = "_showId";
	public static final String SESSION_SHOW_NAME = "_showName";
	// staff 0, exhibitors  1, audience 2, media 3
	public static final String SESSION_SHOW_TYPE = "_showType";

	public static final String AUDIENCE_REGISTER =
			"<p>您好，</p>"
			+ "<p>感谢您申请参观第二届中国(连云港)丝绸之路国际物流博览会。</p>"
			+ "<p>请记住您的观众（ [arg0] ）登陆信息，</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp;用户名： <strong>[arg1]</strong></p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp;密码 ：&nbsp;<strong>[arg2]</strong></p>"
			+ "<p>中国(连云港)丝绸之路国际物流博览会恭候您的光临。</p>"
			+ "<p>&nbsp;</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;中国(连云港)丝绸之路国际物流博览会</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;电话： 86 518 85865601</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;网址：www.lygexpo.com</p>";

	public static final String EXT_REGISTER =
			"<p>您好，</p>"
			+ "<p>感谢您申请参加第二届中国(连云港)丝绸之路国际物流博览会，您的申请已受理，请等待审核。</p>"
			+ "<p>请记住您的展商 （[arg0]） 登陆信息，</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp;用户名： <strong>[arg1]</strong></p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp;密码 ：&nbsp;<strong>[arg2]</strong></p>"
			+ "<p>中国(连云港)丝绸之路国际物流博览会恭候您的光临。</p>"
			+ "<p>&nbsp;</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;中国(连云港)丝绸之路国际物流博览会</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;电话： 86 518 85865601</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;网址：www.lygexpo.com</p>";

	public static final String EXT_APPROVED =
			"<p>您好，</p>"
			+ "<p>您的参展申请（[arg0]）已通过审核，感谢您参加9月22日在连云港举办的第二届中国(连云港)丝绸之路国际物流博览会。</p>"
			+ "<p>届时恭候您的光临！</p>"
			+ "<p>&nbsp;</p>"
			+ "<p>这封邮件为连博会官方通知确认邮件，请不要回复该邮件。</p>"
			+ "<p>&nbsp;</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;中国(连云港)丝绸之路国际物流博览会</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;电话： 86 518 85865601</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;网址：www.lygexpo.com</p>";


	public static final String EXT_REFUSE = "<p>您好，</p>"
			+ "<p>对不起，很遗憾地告诉您，您的中国丝绸之路国际物流博览会参展申请（[arg0]），由于以下原因：</p>"
			+ "<p><strong>[arg1]</strong></p>"
			+ "<p>未能通过审核。</p>"
			+ "<p>您可以再次访问www.lygexpo.com 进行申请。为保障您能顺利完成申请流程,请留意驳回原因以确保申请成功。</p>"
			+ "<p>&nbsp;</p>"
			+ "<p>&nbsp;</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;中国(连云港)丝绸之路国际物流博览会</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;电话： 86 518 85865601</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;网址：www.lygexpo.com</p>";

	public static final String VISITOR_APPROVED = "<p>您好，</p>"
			+ "<p>您的连博会现场证件申请( [arg0] 的现场证件申请)已通过审核，感谢您参加9月22日在连云港举办的第二届中国(连云港)丝绸之路国际物流博览会。</p>"
			+ "<p>现场证件用于展会期间进出展馆使用，您可以打印下方二维码至展会以方便现场办理证件。</p>"
			+ "<p>&nbsp;</p>"
			+ "<p>这封邮件为连博会官方通知确认邮件，请勿回复该邮件。</p>"
			+ "<p><img src='[arg1]'/>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</p>"
			+ "<p>&nbsp;</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;中国(连云港)丝绸之路国际物流博览会</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;电话： 86 518 85865601</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;网址：www.lygexpo.com</p>";

	public static final String VISITOR_REFUSE = "<p>您好，</p>"
			+ "<p>对不起，很遗憾地告诉您，您的连博会现场证件申请( [arg0] 的现场证件申请)由于以下原因：</p>"
			+ "<p><strong>[arg1]</strong></p>"
			+ "<p>未能通过审核。</p>"
			+ "<p>您可以再次访问www.lygexpo.com 或通过手机客户端进行申请。为保障您能顺利完成申请流程,请留意驳回原因以确保申请成功。</p>"
			+ "<p>&nbsp;</p>"
			+ "<p>&nbsp;</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;中国(连云港)丝绸之路国际物流博览会</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;电话： 86 518 85865601</p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;网址：www.lygexpo.com</p>";

	public static final String EXT_SUBJECT_OBJECTION = "抱歉，您的连博会展商申请被驳回";
	public static final String EXT_SUBJECT_REGISTER = "您的连博会展商申请已受理，请等待审核";
	public static final String EXT_SUBJECT_APPROVED = "恭喜，您的连博会展商申请已通过";
	public static final String AUDIENCE_SUBJECT_REGISTER = "关于您的连博会观众账号";
	public static final String VISITOR_SUBJECT_OBJECTION = "抱歉，您的连博会现场证件申请被驳回";
	public static final String VISITOR_SUBJECT_APPROVED = "恭喜，您的连博会现场证件申请已通过";

	public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";


	//穆东成提出意见，8月25日晚
	public static final List<String> JS_CITIES = new ArrayList<String>(){{ add("南京");add("徐州");add("苏州");add("常州");add("淮安");add("南通");add("宿迁");add("泰州");add("无锡");add("盐城");add("扬州");add("镇江");add("江苏");}};

	public static final List<String> OTHER_CONTRIES = new ArrayList<String>(){{add("日本");add("英国");add("美国");add("德国");add("法国");add("韩国");add("台湾");add("香港");add("巴西");add("阿根廷");add("墨西哥");add("巴基斯坦");add("印度");add("土库曼斯坦");add("吉尔吉斯斯坦");add("塔吉克斯坦");
		add("哈萨克斯坦");add("西班牙");add("瑞典");add("澳大利亚");add("加拿大");add("意大利");add("瑞士");add("新加坡");add("乌兹别克斯坦");add("泰国");add("马来西亚");add("新西兰");add("俄罗斯");add("荷兰");add("巴勒斯坦");add("阿拉伯");add("挪威");add("澳门");}};
		
	public static final String LIANYUNGANG_KEYWORDS_STR ="连云港";
	
	public static final String LIANYUNGANG_STR ="连云港市内企业";
	
	public static final String JIANGSU_STR = "江苏省内（除连云港市）企业";
	
	public static final String CHINA_STR = "中国境内（除江苏省）企业";
	
	public static final String BORDER_STR = "境外企业";
	
	public static final String MONITOR_ZYTZ_CONTENT = "<p>您好，</p>"
			+ "<p>连博会网站重要通知新闻更新了一条："
			+ "<p><strong>[arg0]</strong></p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;中国(连云港)丝绸之路国际物流博览会</p>";

	
	public static final String MONITOR_HYZX_CONTENT = "<p>您好，</p>"
			+ "<p>连博会网站行业资讯新闻更新了一条："
			+ "<p><strong>[arg0]</strong></p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;中国(连云港)丝绸之路国际物流博览会</p>";
	public static final String MONITOR_ZLZX_CONTENT = "<p>您好，</p>"
			+ "<p>连博会网站展览资讯新闻更新了一条："
			+ "<p><strong>[arg0]</strong></p>"
			+ "<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;中国(连云港)丝绸之路国际物流博览会</p>";
	public static final String MONITOR_ZLZX_TITLE = "连博会网站展览资讯新闻更新";
	public static final String MONITOR_HYZX_TITLE = "连博会网站行业资讯新闻更新";
	public static final String MONITOR_ZYTZ_TITLE = "连博会网站重要通知新闻更新";
}
