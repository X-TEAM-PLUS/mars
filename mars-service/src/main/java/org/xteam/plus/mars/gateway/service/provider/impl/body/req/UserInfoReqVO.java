package org.xteam.plus.mars.gateway.service.provider.impl.body.req;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.Inet4Address;

/**
 * 用户信息请求对象
 */
public class UserInfoReqVO implements Serializable {

    private BigDecimal userId;

    private Integer start;

    private Integer limit;

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }


    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
