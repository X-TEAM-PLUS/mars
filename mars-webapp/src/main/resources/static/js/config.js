//定义日志开关
let LOG_SWITCH = true;
//定义接口地址
let INTERFACE_URL = "/webservice/api/gateway";
let TOKEN = "token";
let userInfo;
//sms延时
let SMS_BUTTON_WAIT=60;
let cardNo = undefined;
let InterFace = {
    //用户信息
    USER_INFO: 'cn.zaoangongcheng.api.gateway.user.detail'
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
    , ME_HEART_CARD_INFO: 'cn.zaoangongcheng.api.gateway.security.user.heartcard.activated.get'
    //账户余额
    , ACCOUNT_INFO: 'cn.zaoangongcheng.api.gateway.security.user.getAccountInfo'
    //保险产品列表
    , INSURANCE_LIST: 'cn.zaoangongcheng.api.gateway.insurance.product.list'
    //用户保险列表
    , USER_INSURANCE_LIST: 'cn.zaoangongcheng.api.gateway.user.insurance.list'
    //用户保险详情
    , USER_INSURANCE_DETAIL: 'cn.zaoangongcheng.api.gateway.user.insurance.detail'
    //用户健康检查记录列表
    , USER_HEALTH_CHECK_RECORD_LIST: 'cn.zaoangongcheng.api.gateway.user.heartcheck.record.list'
    //健康卡包列表
    , USER_HEALTH_CARD_LIST: 'cn.zaoangongcheng.api.gateway.user.heartcard.list'
    //健康卡详情与购卡人信息
    , USER_HEALTH_CARD_DETAIL: 'cn.zaoangongcheng.api.gateway.user.heartcard.get'
    //获取用户提现记录列表
    , USER_WITHDRAW_RECORD_LIST: 'cn.zaoangongcheng.api.gateway.user.withdraw.record.list'
    //获取用户提现记录详情
    , USER_WITHDRAW_RECORD_DETAIL: 'cn.zaoangongcheng.api.gateway.user.withdraw.record.detail'
    //获取某用户的补贴列表
    , USER_SUBSIDY_LIST: 'cn.zaoangongcheng.api.gateway.user.subsidy.list'
    //银行列表
    , BANK_LIST: 'cn.zaoangongcheng.api.gateway.bank.list'
    //绑定银行卡
    , BIND_BANK_CARD: 'cn.zaoangongcheng.api.gateway.user.bindBank'
    //账户and银行卡信息查询
    , ACCOUNT_AND_BANK_CARD: 'cn.zaoangongcheng.api.gateway.security.user.accountandbankcard.get'
    //提现申请
    , WITHDRAW_APPLY: 'cn.zaoangongcheng.api.gateway.user.withdraw.apply'
    //预下单接口
    , PAY_UNIFIED_ORDER: 'com.zaoangongcheng.api.gateway.wx.pay.unifiedOrder'
    //微信全局设置
    , WX_GLOBLE_CONFIG: 'com.zaoangongcheng.api.gateway.wx.globle.config'
    //升级社工
    ,APPLY_LEVEL_SOCIAL:'com.zaoangongcheng.gateway.user.userLevelApply.social'
    //升级理事
    ,APPLY_LEVEL_DIRECTOR:'com.zaoangongcheng.gateway.user.userLevelApply.director'
    //升级常任理事
    ,APPLY_LEVEL_STANDING_DIRECTOR:'com.zaoangongcheng.gateway.user.userLevelApply.standing.director'
    //理事申请升级展示用户发展会员情况页面
    , TEAM_COUNT: 'cn.zaoangongcheng.api.gateway.user.team.count'
    //获取短信验证码
    , GET_SMS_CODE: 'cn.zaoangongcheng.api.gateway.verification.code'
    //验证码登录
    , SMS_LOGIN: 'cn.zaoangongcheng.api.gateway.smscode.login'
    //我的TM
    , MY_TEAM: 'com.zaoangongcheng.gateway.user.getMeTeamCount'
    //上传体检结果
    ,SUBMIT_CHECK_RESULT:'com.zaoangongcheng.gateway.user.submitUserDetection'
    //获取最后检查记录信息
    ,GET_LAST_CHECK_RECORD:'com.zaoangongcheng.gateway.user.UserDetection.getlast'
    //获取用户检查记录详情
    ,GET_CHECK_RECORD_DETAIL:'cn.zaoangongcheng.api.gateway.security.user.checkRecord.detail'
    //获取用户二维码工作证
    ,GET_QR_CODE:'cn.zaoangongcheng.api.gateway.user.qrcode.get'
    //获取地市信息
    ,GET_AREA_INFO: 'cn.zaoangongcheng.api.gateway.area.detail'
    //卡密购买
    ,CARD_KEYS_BUY: 'com.zaoangongcheng.gateway.user.submitUserCardKeys'
}

/**
 * 定义返回状态码
 * @type {{SUCCESS: number, UNKNOW: number, UNAUTHORIZED: number, MISSING_PARAMETERS: number, ILLEGAL_PARAMETERS: number, USER_ID_NOT_HIVE: number}}
 */
let ResponseCode = {
    SUCCESS: 10000
    , UNKNOW: 20000
    , UNAUTHORIZED: 20001
    , MISSING_PARAMETERS: 40001
    , ILLEGAL_PARAMETERS: 40002
    , BUSINESS_FAILED: 40004
    , SELL_USER_NOT_FIND: 40015
    , OBJECT_ISNULL: 40005
    , CARD_ALREAD_BIND: 40006
    , APPLY_AMOUNT_ZER: 40007
    , USER_ID_NOT_HIVE: 40008
    , AMOUNT_NOT_ENOUGH: 40009
    , BANK_ID_ERROR: 40010
    , PRODUCT_ID_NOT_HIVE: 40011
    , DIFFERENT_REAL_NAME: 40012
    , BANK_CARD_IS_BINDED: 40013
    , CARD_NO_NOT_FIND: 40014
    ,TOKEN_IS_EFFICACY:40015
    ,USER_LEVEL_LIMIT_ERROR:40016
    ,USER_LEVEL_WAY_IS_NOT_NULL:40017
};

/**
 * 业绩指标
 * @type {{DIRECTOR: number, STANDING_DIRECTOR: number}}
 */
let PerformanceIndex ={
    DIRECTOR:500
    ,STANDING_DIRECTOR:1500
};

/**
 * 用户级别
 * @type {{VISITOR: number, MEMBER: number, WORKER: number, DIRECTOR: number, STANDING_DIRECTOR: number}}
 */
let UserLeve = {
    VISITOR: 0
    , MEMBER: 1
    , WORKER: 2
    , DIRECTOR: 3
    , STANDING_DIRECTOR: 4
};

/**
 * 跳回首页地址
 * 预留方法，会校验微信端内，端外
 * @returns {string}
 */
function goIndex() {
    return "weixin/goOauth?backUrl=index.html";
}
