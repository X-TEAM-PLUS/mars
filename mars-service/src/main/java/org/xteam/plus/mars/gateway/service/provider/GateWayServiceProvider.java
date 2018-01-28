package org.xteam.plus.mars.gateway.service.provider;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xteam.plus.mars.gateway.token.Token;
import org.xteam.plus.mars.service.provider.AbstractServiceProvider;

import javax.annotation.Resource;


/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2017-11-28
 * Time: 14:28
 * 功能: 网关服务提供者
 */

@RestController
@RequestMapping("/api")
public class GateWayServiceProvider extends AbstractServiceProvider {

    @Resource
    private GateWayServiceProxy gateWayServiceProxy;

    /**
     * 网关接口请求
     *
     * @param httpRequestBody
     * @return int
     */
    @RequestMapping("/gateway")
    public HttpResponseBody gateway(@RequestBody HttpRequestBody httpRequestBody) throws Exception{
        return gateWayServiceProxy.gateWay(httpRequestBody);
    }


    /**
     * 获取Token信息
     *
     * @param tokenString
     * @return int
     */
    @RequestMapping("/token/get")
    public Token getToken(@RequestBody String  tokenString) throws Exception{
        Token token = null;
        //先取缓存
        try{
           // token = cacheUtils.getToken(tokenString);

        }catch (Exception e){
            logError("获取通道信息异常",e);
        }
        //返回
        return token;
    }
}
