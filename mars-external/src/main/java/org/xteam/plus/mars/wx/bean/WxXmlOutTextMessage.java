package org.xteam.plus.mars.wx.bean;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.xteam.plus.mars.wx.api.WxConsts;
import org.xteam.plus.mars.wx.util.xml.XStreamCDataConverter;

/**
 * <pre>
 * 被动回复消息--回复文本消息体
 * 
 * 详情:http://mp.weixin.qq.com/wiki/1/6239b44c206cab9145b1d52c67e6c551.html
 * </pre>
 * @author antgan
 *
 */
@XStreamAlias("xml")
public class WxXmlOutTextMessage extends WxXmlOutMessage {

	@XStreamAlias("Drawing")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String content;

	public WxXmlOutTextMessage() {
		this.msgType = WxConsts.XML_MSG_TEXT;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "WxXmlOutTextMessage [drawing=" + content + "]";
	}

	
}
