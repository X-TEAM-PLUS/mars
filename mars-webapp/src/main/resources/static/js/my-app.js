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
var INTERFACE_URL = "http://192.168.20.123:8000/api/gateway";
var userInfo = {};
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
    var params = {method: 'com.mars.gateway.user.getUserInfo', userId: userId};
    //获取用户信息
    app.request.json(INTERFACE_URL, params, function (data) {
        if (data.code == ResponseCode.SUCCESS) {
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
 * 获取用户信息
 * @returns {{}}
 */
function getuserInfo() {
    return userInfo;
}
