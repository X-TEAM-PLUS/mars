package org.xteam.plus.mars.gateway.service.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.xteam.plus.mars.cache.CacheUtils;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.gateway.token.Token;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 网关接口代理类
 */
@Component
public class GateWayServiceProxy extends Logging implements ApplicationContextAware {

    @Resource
    private CacheUtils cacheUtils;

    private static Map<String, GateWayService> gateWayServiceHashMap = new HashMap<String, GateWayService>();

    private ApplicationContext applicationContext;

    protected void init() {
        try {
            String[] beanNames = applicationContext.getBeanNamesForType(GateWayService.class);
            for (String beanName : beanNames) {
                GateWayService gateWayService = (GateWayService) applicationContext.getBean(beanName);
                gateWayServiceHashMap.put(gateWayService.getMethodName().toLowerCase(), gateWayService);
            }
        } catch (Exception e) {
           logError("加载网关接口异常",e);
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
        long beginTime = System.currentTimeMillis();
        logInfo("请求参数:" + JsonUtils.toJSON(httpRequestBody));
        HttpResponseBody httpResponseBody = null;
        if(StringUtils.isEmpty(httpRequestBody.getMethod())){
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        String method = httpRequestBody.getMethod().toLowerCase();
        try {
            //根据方法名 获取服务接口
            GateWayService gateWayService = getGateWayService(method);
            if (gateWayService == null) {
                logWarning("未获取相应的接口["+method+"]");
                httpResponseBody = new HttpResponseBody(GlobalErrorMessage.UNKNOW);
            } else {

                //如果带token
                if (!StringUtils.isEmpty(httpRequestBody.getToken())) {
                    //获取取缓存
                    Token  token = cacheUtils.getToken(httpRequestBody.getToken());
                    httpRequestBody.setUserId(token.getUserId());
                }

                //调用接口
                httpResponseBody = gateWayService.gateWay(httpRequestBody);
            }
        } catch (Exception e) {
            logError("调用接口["+ method  +  "]异常", e);
            httpResponseBody = new HttpResponseBody(GlobalErrorMessage.BUSINESS_FAILED);
        }finally {
            logInfo("接口["+method+"]返回参数:" + JsonUtils.toJSON(httpResponseBody));
            logInfo("调用接口["+method +"] 耗时 " + (System.currentTimeMillis()-beginTime) + "ms");
        }
        //返回
        return httpResponseBody;
    }

}
