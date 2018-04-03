package org.xteam.plus.mars.gateway.service.provider;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.xteam.plus.mars.cache.CacheUtils;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.gateway.token.Token;
import org.xteam.plus.mars.service.provider.AbstractServiceProvider;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


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
    public HttpResponseBody gateway(HttpServletRequest request, HttpRequestBody httpRequestBody) throws Exception {
        // post dataformat 方式提交，设置附件
        if (request instanceof StandardMultipartHttpServletRequest) {
            StandardMultipartHttpServletRequest multipartHttpServletRequest = (StandardMultipartHttpServletRequest) request;
            MultiValueMap<String, MultipartFile> multipartFileMultiValueMap = multipartHttpServletRequest.getMultiFileMap();
            httpRequestBody.setMultipartFileHashMap((LinkedMultiValueMap<String, MultipartFile>) multipartFileMultiValueMap);
        }

//        String url = request.getScheme() + "://";
//        url += request.getHeader("host");
//        url += request.getRequestURI();
//        if (request.getQueryString() != null) {
//            url += "?" + request.getQueryString();
//        }
//        httpRequestBody.setRequestUrl(url);

        return gateWayServiceProxy.gateWay(httpRequestBody);
    }

}
