package org.xteam.plus.mars.wx.api;


import org.xteam.plus.mars.wx.exception.WxErrorException;

/**
 * 微信异常处理器接口
 * 
 * @author antgan
 *
 */
public interface WxErrorExceptionHandler {

	public void handle(WxErrorException e);

}
