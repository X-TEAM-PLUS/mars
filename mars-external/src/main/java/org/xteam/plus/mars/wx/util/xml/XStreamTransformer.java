package org.xteam.plus.mars.wx.util.xml;


import com.thoughtworks.xstream.XStream;
import org.xteam.plus.mars.wx.bean.*;
import org.xteam.plus.mars.wx.bean.result.GetPublicKeyResult;
import org.xteam.plus.mars.wx.bean.result.PayBankInfoResult;
import org.xteam.plus.mars.wx.bean.result.PayPocketMoneyResult;
import org.xteam.plus.mars.wx.bean.result.UnifiedOrderResult;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 指定类的专属XStream
 * @author antgan
 *
 */
public class XStreamTransformer {

	protected static final Map<Class, XStream> CLASS_2_XSTREAM_INSTANCE = configXStreamInstance();

	/**
	 * xml -> pojo
	 *
	 * @param clazz
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(Class<T> clazz, String xml) {
		T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(xml);
		return object;
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXml(Class<T> clazz, InputStream is) {
		T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(is);
		return object;
	}

	/**
	 * pojo -> xml
	 *
	 * @param clazz
	 * @param object
	 * @return
	 */
	public static <T> String toXml(Class<T> clazz, T object) {
		return CLASS_2_XSTREAM_INSTANCE.get(clazz).toXML(object);
	}

	private static Map<Class, XStream> configXStreamInstance() {
		Map<Class, XStream> map = new HashMap<Class, XStream>();
		map.put(PayBankInfoResult.class,config_PayBankInfoResult());
		map.put(WxPayAnotherBank.class,config_WxPayAnotherBank());

		map.put(PayPocketMoneyResult.class,config_PayPocketMoney());
		map.put(WxPayPocketMoney.class,config_PayPocket());

		map.put(GetPublicKeyResult.class,config_GetPublicKey());
		map.put(UnifiedOrderResult.class,config_UnifiedOrderResult());
		map.put(WxGetPublicKey.class,config_WxGetPublicKey());
		map.put(WxUnifiedOrder.class,config_WxUnifiedOrder());
		map.put(WxXmlMessage.class, config_WxXmlMessage());
		map.put(WxXmlOutNewsMessage.class, config_WxXmlOutNewsMessage());
		map.put(WxXmlOutTextMessage.class, config_WxXmlOutTextMessage());
		map.put(WxXmlOutImageMessage.class, config_WxXmlOutImageMessage());
		map.put(WxXmlOutVideoMessage.class, config_WxXmlOutVideoMessage());
		map.put(WxXmlOutVoiceMessage.class, config_WxXmlOutVoiceMessage());
		return map;
	}

	private static XStream config_PayPocket(){
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxPayPocketMoney.class);
		return xstream;
	}
	private static XStream config_PayPocketMoney(){
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(PayPocketMoneyResult.class);
		return xstream;
	}
	private static XStream config_PayBankInfoResult(){
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(PayBankInfoResult.class);
		return xstream;
	}
	private static XStream config_WxPayAnotherBank(){
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxPayAnotherBank.class);
		return xstream;
	}
	private static XStream config_WxGetPublicKey(){
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxGetPublicKey.class);
		return xstream;
	}
	private static XStream config_GetPublicKey(){
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(GetPublicKeyResult.class);
		return xstream;
	}
	private static XStream config_UnifiedOrderResult(){
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(UnifiedOrderResult.class);
		return xstream;
	}
	private static XStream config_WxUnifiedOrder(){
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxUnifiedOrder.class);
		return xstream;
	}

	private static XStream config_WxXmlMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlMessage.class);
		xstream.processAnnotations(WxXmlMessage.ScanCodeInfo.class);
		xstream.processAnnotations(WxXmlMessage.SendPicsInfo.class);
		xstream.processAnnotations(WxXmlMessage.SendPicsInfo.Item.class);
		xstream.processAnnotations(WxXmlMessage.SendLocationInfo.class);
		return xstream;
	}

	private static XStream config_WxXmlOutImageMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlOutMessage.class);
		xstream.processAnnotations(WxXmlOutImageMessage.class);
		return xstream;
	}

	private static XStream config_WxXmlOutNewsMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlOutMessage.class);
		xstream.processAnnotations(WxXmlOutNewsMessage.class);
		xstream.processAnnotations(WxXmlOutNewsMessage.Item.class);
		return xstream;
	}

	private static XStream config_WxXmlOutTextMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlOutMessage.class);
		xstream.processAnnotations(WxXmlOutTextMessage.class);
		return xstream;
	}

	private static XStream config_WxXmlOutVideoMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlOutMessage.class);
		xstream.processAnnotations(WxXmlOutVideoMessage.class);
		xstream.processAnnotations(WxXmlOutVideoMessage.Video.class);
		return xstream;
	}

	private static XStream config_WxXmlOutVoiceMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WxXmlOutMessage.class);
		xstream.processAnnotations(WxXmlOutVoiceMessage.class);
		return xstream;
	}

}
