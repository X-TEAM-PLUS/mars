var theme = 'ios';
if (location.href.indexOf('theme=md') >= 0) theme = 'md';
var plugin = {
    params: {
        theme: theme,
        root: '#app'
    }
};
if (Framework7.use) Framework7.use(plugin);
else if (Framework7.Class && Framework7.Class.use) Framework7.Class.use(plugin);
var $$ = Dom7;

//定义接口地址
var INTERFACE_URL = "http://192.168.31.139:8080/webservice/api/gateway";
var userInfo = {};
var InterFace = {
    USER_INFO: 'cn.zaoangongcheng.api.gateway.user.getUserInfo'
    , QUESTION_LIST: 'cn.zaoangongcheng.api.gateway.global.question.list'
    , QUESTION_DETAIL: 'cn.zaoangongcheng.api.gateway.global.question.detail'
    , MESSAGE_LIST: 'cn.zaoangongcheng.api.gateway.security.message.list'
    , MESSAGE_MARK: 'cn.zaoangongcheng.api.gateway.security.message.mark'
    , MESSAGE_REMOVE: 'cn.zaoangongcheng.api.gateway.security.message.remove'
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

/**
 * 加载用户视图
 * @param url
 * @param params
 */
function loadUserView(userId) {
    var params = {method: InterFace.USER_INFO, userId: userId};
    //获取用户信息
    app.request.json(INTERFACE_URL, params, function (data) {
        if (ResponseCode.SUCCESS == data.code) {
            console.info("获取用户信息成功.")
            var bizContent = JSON.parse(data.bizContent);
            setUserInfo(bizContent);
            var userViewDomId = "user-view";
            switch (bizContent.userLevel) {
                case UserLeve.VISITOR:
                    loadData(bizContent, userViewDomId, "show-member-view");
                    break;
                case UserLeve.MEMBER:
                    loadData(bizContent, userViewDomId, "show-gongyi-view");
                    break;
                case UserLeve.WORKER:
                    loadData(bizContent, userViewDomId, "show-shegong-view");
                    break;
                case UserLeve.DIRECTOR:
                    loadData(bizContent, userViewDomId, "show-lishi-view");
                    break;
                case UserLeve.STANDING_DIRECTOR:
                    loadData(bizContent, userViewDomId, "show-changwulishi-view");
                    break;
                default:
                    break;
            }
        }
    });
}

/**
 * 获取 bizContent
 * @param url  请求地址
 * @param domId
 * @param templateId
 */
function loadBizContent(url, params, domId, templateId) {
    app.request.json(url, params, function (data) {
        var bizContent = JSON.parse(data.bizContent);
        loadData(bizContent, domId, templateId);
    });
}

/**
 * 加载数据
 * @param json
 * @param domId
 * @param templateId
 */
function loadData(json, domId, templateId) {
    // Template
    var template = document.getElementById(templateId).innerHTML;
    var compiled = Template7(template).compile();
    var compiledRendered = compiled(json);
    document.getElementById(domId).innerHTML = compiledRendered;
}

/**
 * 设置用户信息
 * @param json
 */
function setUserInfo(json) {
    userInfo = json;
}


/**
 * 删除系统消息
 * @param messageId
 */
function removeSystemMessage(messageId) {
    var bizContent = {messageId: messageId};
    var params = {
        method: InterFace.MESSAGE_REMOVE,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };
    //删除消息
    app.request.json(INTERFACE_URL, params, function (data) {
        if (ResponseCode.SUCCESS == data.code) {
            console.info("删除消息成功.")
        }
    });
}


/**
 * 标记已读消息
 * @param messageId
 */
function markSystemMessage(messageId) {
    var bizContent = {messageId: messageId};
    var params = {
        method: InterFace.MESSAGE_MARK,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };
    //标记消息已读
    app.request.json(INTERFACE_URL, params, function (data) {
        if (ResponseCode.SUCCESS == data.code) {
            document.getElementById("messageId-" + messageId).className = " item-title color-gray ";
            console.info("标记已读消息成功.")
        }
    });
}

/**
 * 爱补贴
 */
function loveBuTieClick() {
    if (userInfo.userId && userInfo.userLevel >= 2) {
        memberView.router.navigate('/lovebutie/', {
            history: true
        });
    } else {
        memberView.router.navigate('/member_rk/', {
            history: true
        });
    }
}

/**
 * 小卡包
 */
function xiaoKaBaoClick() {
    if (userInfo.userId && userInfo.userLevel >= 1) {
        memberView.router.navigate('/xiaokabao/', {
            history: true
        });
    } else {
        memberView.router.navigate('/member_rk/', {
            history: true
        });
    }
}

/**
 * 健康卡
 */
function jianKangKaClick() {
    if (userInfo.userId && userInfo.userLevel >= 1) {
        memberView.router.navigate('/jiankangka/', {
            history: true
        });
    } else {
        memberView.router.navigate('/member_rk/', {
            history: true
        });
    }
}