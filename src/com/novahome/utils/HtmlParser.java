package com.novahome.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.novahome.data.service.ZlzxService;

public class HtmlParser {

	private static DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder domBuilder;
	private static String html = "<h1>&nbsp; &nbsp; &nbsp; &nbsp; 专业化展览布局助推外贸转型<br /> &nbsp; &nbsp; &nbsp; &nbsp;第117届广交会&bull;出口展区精华汇</h1>  <p>发布日期：2015-03-20&nbsp;</p>  <p>&nbsp;</p>  <p><img src='http://www.cantonfair.org.cn/fileserver/cms/2015-03/201503200347144714833.jpg' style='margin:0px' /></p>  <p>&nbsp; &nbsp;忽如一夜春风来，千树万树梨花开。转眼半载，全球最大规模的国际贸易盛会&mdash;&mdash;第117届中国进出口商品交易会（简称&ldquo;广交会&rdquo;）即将拉开帷幕，于4月15日-5月5日分三期举办。这里汇集16大类商品、50个展区，展览面积达到118万平方米，展位总规模超过6万个，蕴含无限商机！接下来，请您跟随我们的镜头，看看第117届广交会出口展区有哪些精彩亮点。</p>  <p><strong>快照一：迈向专业化，紧跟需求优化、培育展览题材</strong></p>  <p>&nbsp; &nbsp;专业化是新常态下会展业的发展趋势。为顺应展览市场规律，提升参展体验，第117届从优化展区设置、培育展品专区和培育新题材入手，巩固、深化近两届广交会在专业化发展上的成果：</p>  <p>&nbsp; &nbsp;在优化展区设置、增设展品专区方面，除了将第三期的医药保健品、医疗器械/耗材/敷料展区合并为医药保健品及医疗器械展区外，第117届广交会在前两届的基础上，全面推广、优化展区内展品分类集中摆放，把设置展品专区的展区范围扩大至23个，覆盖第一期大多数展区，以及第二、三期部分展区。同时，加强新能源、宠物用品和户外水疗新题材的培育，面向市场筛选出众多的优质企业，许多行业龙头积极报名参展，大大提高了新题材的档次。</p>  <p>&nbsp;</p>  <p><img src='http://www.cantonfair.org.cn/fileserver/cms/2015-03/201503200348114811365.jpg' style='margin:0px' /></p>  <p>&nbsp;</p>  <p><strong>快照二：推进制度化，完善制度推动组展优化</strong></p>  <p>&nbsp; &nbsp;规范管理，制度先行。第117届广交会继续在制度建设方面务实耕耘，出台了《广交会出口展组展业务线上管理规范》，推进无纸化办公，简化办事流程，为参展企业提供更便捷的服务；修订《广交会出口展展品质量及贸易纠纷投诉监控办法》，促进企业规范签约行为，完善监控办法，打造一个祥和有序的展会环境。</p>  <p>&nbsp;</p>  <p><strong>快照三：提升国际化，中外产品同台竞技</strong></p>  <p>&nbsp; &nbsp;在上届基础上继续推动进出口融合，第117届广交会第一期展馆A区5.2展厅，以及第三期展馆C区15.1展厅，将分别有境外的家用电器、家用纺织品参展企业，与境内同题材参展企业相邻展出。中外企业同台互动交流，将激发出更好的展览效果，身处其中，你将亲身领略到全球产品琳琅满目、尽收眼底的绝妙体验。</p> ";
	private static String html2 = "<h1>&nbsp; &nbsp; &nbsp; &nbsp; 专业化展览布局助推外贸转型<br /> &nbsp; &nbsp; &nbsp; &nbsp;第117届广交会&bull;出口展区精华汇</h1>  <p>发布日期：2015-03-20&nbsp;</p>  <p>&nbsp;</p>    <p>&nbsp; &nbsp;忽如一夜春风来，千树万树梨花开。转眼半载，全球最大规模的国际贸易盛会&mdash;&mdash;第117届中国进出口商品交易会（简称&ldquo;广交会&rdquo;）即将拉开帷幕，于4月15日-5月5日分三期举办。这里汇集16大类商品、50个展区，展览面积达到118万平方米，展位总规模超过6万个，蕴含无限商机！接下来，请您跟随我们的镜头，看看第117届广交会出口展区有哪些精彩亮点。</p>  <p><strong>快照一：迈向专业化，紧跟需求优化、培育展览题材</strong></p>  <p>&nbsp; &nbsp;专业化是新常态下会展业的发展趋势。为顺应展览市场规律，提升参展体验，第117届从优化展区设置、培育展品专区和培育新题材入手，巩固、深化近两届广交会在专业化发展上的成果：</p>  <p>&nbsp; &nbsp;在优化展区设置、增设展品专区方面，除了将第三期的医药保健品、医疗器械/耗材/敷料展区合并为医药保健品及医疗器械展区外，第117届广交会在前两届的基础上，全面推广、优化展区内展品分类集中摆放，把设置展品专区的展区范围扩大至23个，覆盖第一期大多数展区，以及第二、三期部分展区。同时，加强新能源、宠物用品和户外水疗新题材的培育，面向市场筛选出众多的优质企业，许多行业龙头积极报名参展，大大提高了新题材的档次。</p>  <p>&nbsp;</p>  <p><img src='http://www.cantonfair.org.cn/fileserver/cms/2015-03/201503200348114811365.jpg' style='margin:0px' /></p>  <p>&nbsp;</p>  <p><strong>快照二：推进制度化，完善制度推动组展优化</strong></p>  <p>&nbsp; &nbsp;规范管理，制度先行。第117届广交会继续在制度建设方面务实耕耘，出台了《广交会出口展组展业务线上管理规范》，推进无纸化办公，简化办事流程，为参展企业提供更便捷的服务；修订《广交会出口展展品质量及贸易纠纷投诉监控办法》，促进企业规范签约行为，完善监控办法，打造一个祥和有序的展会环境。</p>  <p>&nbsp;</p>  <p><strong>快照三：提升国际化，中外产品同台竞技</strong></p>  <p>&nbsp; &nbsp;在上届基础上继续推动进出口融合，第117届广交会第一期展馆A区5.2展厅，以及第三期展馆C区15.1展厅，将分别有境外的家用电器、家用纺织品参展企业，与境内同题材参展企业相邻展出。中外企业同台互动交流，将激发出更好的展览效果，身处其中，你将亲身领略到全球产品琳琅满目、尽收眼底的绝妙体验。</p> ";

	private static String reverseStr = "@~%^";
	private static final Logger logger = Logger.getLogger(HtmlParser.class);
	private static final String ERROR_MSG = "解析错误，无html内容"; 
	public static final String NO_FOUND_MSG = "请前往喵星寻找该请求";
	public static String transferToHtml(String content)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html>");
		buffer.append(content);
		buffer.append("</html>");
		return buffer.toString();
	}
	
	public static String extractPicFromHtml(String content)
	{
		String pictureUrl = "";
		if(domBuilder == null)
		{
			synchronized(DocumentBuilder.class)
			{
				if(domBuilder == null)
				{
					try {
						domBuilder = domFactory.newDocumentBuilder();
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		String tmp = replaceReverse(transferToHtml(content));
		InputSource is = new InputSource();  
        is.setCharacterStream(new StringReader(tmp));
        Document doc = null;
		
		try {
			doc = domBuilder.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(doc == null)
		{
			logger.warn(ERROR_MSG);
		}
		NodeList nodes = doc.getElementsByTagName("img");  
		for(int i = 0; i < nodes.getLength(); i++){
			Element ele = (Element)nodes.item(i);
			pictureUrl = ele.getAttribute("src");
			break;
		}
		return pictureUrl;
	}
	
	public static void main(String[] args)
	{
		System.out.println(HtmlParser.extractPicFromHtml(html));
		System.out.println(HtmlParser.extractPicFromHtml(html2));
		System.out.println(HtmlParser.extractPicFromHtml(html));
		System.out.println(HtmlParser.extractPicFromHtml(html2));
		/*try {
			domBuilder = domFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tmp = replaceReverse("<html>" + html + "</html>");
		InputSource is = new InputSource();  
        is.setCharacterStream(new StringReader(tmp));

        Document doc = null;
		
			try {
				doc = domBuilder.parse(is);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(doc == null)
				System.out.println("nulll");
			NodeList nodes = doc.getElementsByTagName("img");  
			for(int i = 0; i < nodes.getLength(); i ++){
				Element ele = (Element)nodes.item(i);
				System.out.println(ele.getAttribute("src"));
			
			}
			NodeList nodes2 = doc.getElementsByTagName("p");  
			for(int i = 0; i < nodes2.getLength(); i ++){
				
				Element ele = (Element)nodes2.item(i);
				
					System.out.println("haha:"+ele.getTextContent()+ "haha//");
				
			
			}*/
	}
	
	/*
	 * 替代转义字符
	 */
	private static String replaceReverse(String str){
		return str.replace("&", reverseStr);
	}
	
	/*
	 * 恢复转义字符
	 */
	private static String restoreReverse(String str){
		return str.replace(reverseStr, "&");
	}
}
