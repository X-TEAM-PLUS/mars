package org.xteam.plus.mars.gateway.service.provider.impl.body.rsp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserRelationRspVO implements Serializable {

    /**
     * 关系流水号
     */
    private java.math.BigDecimal relationId;

    /**
     * 推荐人ID
     */
    private java.math.BigDecimal refereeUserId;

    /**
     * 用户ID
     */
    private java.math.BigDecimal userId;

    /**
     * 更新时间
     */
    private java.util.Date updated;

    /**
     * 创建时间
     */
    private java.util.Date created;

    /**
     * 用户信息
     */
    private UserInfoRspVO userInfoRspVO;

    public BigDecimal getRelationId() {
        return relationId;
    }

    public void setRelationId(BigDecimal relationId) {
        this.relationId = relationId;
    }

    public BigDecimal getRefereeUserId() {
        return refereeUserId;
    }

    public void setRefereeUserId(BigDecimal refereeUserId) {
        this.refereeUserId = refereeUserId;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public UserInfoRspVO getUserInfoRspVO() {
        return userInfoRspVO;
    }

    public void setUserInfoRspVO(UserInfoRspVO userInfoRspVO) {
        this.userInfoRspVO = userInfoRspVO;
    }
}
