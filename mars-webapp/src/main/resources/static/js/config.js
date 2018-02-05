//定义接口地址
var INTERFACE_URL = "http://"+document.domain + ":8080/webservice/api/gateway";
var userInfo = {};
var InterFace = {
    //用户信息
    USER_INFO: 'cn.zaoangongcheng.api.gateway.user.getUserInfo'
    //常见问题
    , QUESTION_LIST: 'cn.zaoangongcheng.api.gateway.global.question.list'
    //问题详情
    , QUESTION_DETAIL: 'cn.zaoangongcheng.api.gateway.global.question.detail'
    //消息列表
    , MESSAGE_LIST: 'cn.zaoangongcheng.api.gateway.security.message.list'
    //标记消息已读
    , MESSAGE_MARK: 'cn.zaoangongcheng.api.gateway.security.message.mark'
    //删除消息
    , MESSAGE_REMOVE: 'cn.zaoangongcheng.api.gateway.security.message.remove'
    //我的健康卡信息
    ,ME_HEART_CARD_INFO:'cn.zaoangongcheng.api.gateway.security.user.heartcard.activated.get'
    //账户余额
    ,ACCOUNT_INFO:'cn.zaoangongcheng.api.gateway.security.user.getAccountInfo'
    //保险产品列表
    ,INSURANCE_LIST:'cn.zaoangongcheng.api.gateway.insurance.product.list'
    //用户保险列表
    ,USER_INSURANCE_LIST:'com.zhaoanyun.gateway.user.insurance.list'
    //用户保险详情
    ,USER_INSURANCE_DETAIL:'com.zhaoanyun.gateway.user.insurance.detail'
    //用户健康检查记录列表
    ,USER_HEALTH_CHECK_RECORD_LIST:'cn.zaoangongcheng.api.gateway.user.heartcheck.record.list'
    //健康卡包列表
    ,USER_HEALTH_CARD_LIST:'cn.zaoangongcheng.api.gateway.user.heartcard.list'
}

/**
 * 定义返回状态码
 * @type {{SUCCESS: number, UNKNOW: number, UNAUTHORIZED: number, MISSING_PARAMETERS: number, ILLEGAL_PARAMETERS: number, USER_ID_NOT_HIVE: number}}
 */
var ResponseCode = {
    SUCCESS: 10000
    , UNKNOW: 20000
    , UNAUTHORIZED: 20001
    , MISSING_PARAMETERS: 40001
    , ILLEGAL_PARAMETERS: 40002
    , USER_ID_NOT_HIVE: 40008
};

/**
 * 用户级别
 * @type {{VISITOR: number, MEMBER: number, WORKER: number, DIRECTOR: number, STANDING_DIRECTOR: number}}
 */
var UserLeve = {
    VISITOR: 0
    , MEMBER: 1
    , WORKER: 2
    , DIRECTOR: 3
    , STANDING_DIRECTOR: 4
}

