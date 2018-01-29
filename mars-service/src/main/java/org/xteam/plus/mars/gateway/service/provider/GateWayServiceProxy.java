package org.xteam.plus.mars.gateway.service.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;

import java.util.HashMap;
import java.util.Map;

/**
 * 网关接口代理类
 */
@Component
public class GateWayServiceProxy extends Logging implements ApplicationContextAware {

    private static Map<String, GateWayService> gateWayServiceHashMap = new HashMap<String, GateWayService>();

    private ApplicationContext applicationContext;

    protected void init() {
        try {
            String[] beanNames = applicationContext.getBeanNamesForType(GateWayService.class);
            for (String beanName : beanNames) {
                GateWayService gateWayService = (GateWayService) applicationContext.getBean(beanName);
                System.out.println(getGateWayService(gateWayService.getMethodName().toLowerCase()));
                gateWayServiceHashMap.put(gateWayService.getMethodName().toLowerCase(), gateWayService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        init();
    }

    /**
     * 根据方法名 获取服务接口
     *
     * @param method
     * @return
     */
    protected GateWayService getGateWayService(String method) {
        return gateWayServiceHashMap.get(method);
    }

    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        logDebug("请求参数:" + JsonUtils.toJSON(httpRequestBody));
        HttpResponseBody httpResponseBody = null;
        try {
            //根据方法名 获取服务接口
            GateWayService gateWayService = getGateWayService(httpRequestBody.getMethod().toLowerCase());
            if (gateWayService == null) {
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.UNKNOW);
            } else {
                //调用接口
                httpResponseBody = gateWayService.gateWay(httpRequestBody);
            }
        } catch (Exception e) {
            logError("调用接口异常", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        }

        logDebug("返回参数:" + JsonUtils.toJSON(httpResponseBody));
        //返回
        return httpResponseBody;
    }

}
