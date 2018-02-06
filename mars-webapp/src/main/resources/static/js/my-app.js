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
 * 我的信息
 */
function meClick() {
    if(isLogin()){
        memberView.router.navigate('/me/', {
            history: true
        });
    }else{
        //去登录
        gotoLogin();
    }
}

/**
 * 去登录
 */
function gotoLogin() {
    memberView.router.navigate('/login/', {
        history: true
    });
}

/**
 * 是否登录
 * @returns {boolean}
 */
function isLogin() {
    if (userInfo.userId) {
      return true;
    } else {
        return false;
    }
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
    if(isLogin()){
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
    }else{
        //去登录
        gotoLogin();
    }


}

/**
 * 爱补贴
 */
function loveBuTieClick() {
    if(isLogin()){
        if ( userInfo.userLevel >= 2) {
            memberView.router.navigate('/lovebutie/', {
                history: true
            });
        } else {
            memberView.router.navigate('/member_rk/', {
                history: true
            });
        }
    }else{
        //去登录
        gotoLogin();
    }


}

/**
 * 小卡包
 */
function xiaoKaBaoClick() {
    if (isLogin()) {
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
    if(isLogin()){
        if (userInfo.userLevel >= 1) {
            memberView.router.navigate('/jiankangka/', {
                history: true
            });
        } else {
            memberView.router.navigate('/member_rk/', {
                history: true
            });
        }
    }else{
        gotoLogin();
    }

}


/**
 * 转账
 */
function zhuanzhangClick() {
    if(isLogin()){
        if (userInfo.card && userInfo.userLevel >= 1) {
            memberView.router.navigate('/zhuanzhang/', {
                history: true
            });
        } else {
            memberView.router.navigate('/bankcard/', {
                history: true
            });
        }
    }else{
        gotoLogin();
    }
}

/**
 * 保险详单
 */
function baoxianxiangdanClick(insuranceOrderId) {
    if(isLogin()){
        if (userInfo.userLevel >= 1) {
            memberView.router.navigate('/baoxianxiangdan/?insuranceOrderId='+insuranceOrderId, {
                history: true
            });
        }
    }else{
        gotoLogin();
    }
}

/**
 * 绑定银行卡
 * @param form
 */
function bindBankCard(form) {
    var bizContent = app.form.convertToData('#'+form);
    var params = {
        method: InterFace.BIND_BANK_CARD,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };
    app.request.post(INTERFACE_URL,params, function (data) {
        console.log(data);
        if (ResponseCode.SUCCESS == data.code) {
            loveBuTieClick();
        }else{
            alert("绑卡失败.");
        }
    });
}
