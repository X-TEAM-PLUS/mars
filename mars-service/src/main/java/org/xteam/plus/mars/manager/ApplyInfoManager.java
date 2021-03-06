package org.xteam.plus.mars.manager;

import org.xteam.plus.mars.domain.ApplyInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能:T_MARS_APPLY_INFO表Manager接口
 */

public interface ApplyInfoManager {

    /**
     * 获取
     *
     * @param applyInfo
     * @return int
     */
    public ApplyInfo get(ApplyInfo applyInfo) throws Exception;

    /**
     * 新增
     *
     * @param applyInfo
     * @return int 记录数
     */
    public int insert(ApplyInfo applyInfo) throws Exception;

    /**
     * 批量新增
     *
     * @param list List<ApplyInfo>
     * @return int  记录数
     */
    public int batchInsert(List<ApplyInfo> list) throws Exception;

    /**
     * 删除
     *
     * @param applyInfo
     * @return int
     */
    public int delete(ApplyInfo applyInfo) throws Exception;

    /**
     * 更新
     *
     * @param applyInfo
     * @return int 记录数
     */
    public int update(ApplyInfo applyInfo) throws Exception;

    /**
     * 查询
     *
     * @param applyInfo
     * @return List<ApplyInfo>
     */
    public List<ApplyInfo> query(ApplyInfo applyInfo) throws Exception;

    /**
     * 查询记录数
     *
     * @param applyInfo
     * @return List<ApplyInfo>
     */
    public Integer queryCount(ApplyInfo applyInfo) throws Exception;

    /**
     * 查询
     *
     * @param applyInfo
     * @return List<ApplyInfo>
     */
    public List<ApplyInfo> queryForUserInfo(ApplyInfo applyInfo) throws Exception;

    /**
     * 查询记录数
     *
     * @param applyInfo
     * @return List<ApplyInfo>
     */
    public Integer queryForUserInfoCount(ApplyInfo applyInfo) throws Exception;

    /**
     * 审批通过
     * @param applyId       申请id
     * @return
     * @throws Exception
     */
    public boolean auditpass(BigDecimal applyId) throws Exception;

    /**
     * 审批驳回
     * @param applyId       申请id
     * @return
     * @throws Exception
     */
    public boolean dismissal(BigDecimal applyId) throws Exception;

    /**
     * 绿色通道
     * @param applyId       申请id
     * @return
     * @throws Exception
     */
    public boolean greenChannel(BigDecimal applyId,int cardNum) throws Exception;
}
