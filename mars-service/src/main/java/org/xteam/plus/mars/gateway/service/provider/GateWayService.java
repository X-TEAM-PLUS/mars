package org.xteam.plus.mars.gateway.service.provider;


/**
 * 网关接口
 */
public interface GateWayService {
    /**
     * 获取接口方法名称
     * @return
     */
    String getMethodName();

    /**
     * 接口调用
     * @param httpRequestBody
     * @return
     * @throws Exception
     */
    HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws  Exception;
}
