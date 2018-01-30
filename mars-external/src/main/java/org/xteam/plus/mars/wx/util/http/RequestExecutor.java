package org.xteam.plus.mars.wx.util.http;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.xteam.plus.mars.wx.exception.WxErrorException;

import java.io.IOException;

/**
 * http请求执行器
 *
 * @param <T>
 *            返回值类型
 * @param <E>
 *            请求参数类型
 */
public interface RequestExecutor<T, E> {

	/**
	 *
	 * @param httpclient
	 *            传入的httpClient
	 * @param uri
	 *            uri
	 * @param data
	 *            数据
	 * @return
	 * @throws WxErrorException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public T execute(CloseableHttpClient httpclient, String uri, E data)
			throws WxErrorException, ClientProtocolException, IOException;

}
