package org.xteam.plus.mars.wx.api;


import org.xteam.plus.mars.wx.bean.WxXmlMessage;
import org.xteam.plus.mars.wx.exception.WxErrorException;

import java.util.Map;

/**
 * <pre>
 * 微信消息拦截器
 * 
 * 实现该接口可以实现消息验证等功能
 * </pre>
 *
 * @author antgan
 */
public interface WxMessageInterceptor {

	/**
	 * 拦截微信消息
	 *
	 * @param wxMessage
	 * @param context
	 *            上下文，如果handler或interceptor之间有信息要传递，可以用这个
	 * @param iService
	 * @return true代表允许通过，false代表不允许通过
	 */
	public boolean intercept(WxXmlMessage wxMessage, Map<String, Object> context, IService iService)
			throws WxErrorException;

}
