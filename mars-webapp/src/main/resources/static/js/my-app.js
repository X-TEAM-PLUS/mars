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
 * 输出日志
 * @param data
 */
function logInfo(data) {
    if (LOG_SWITCH) {
        console.log(data);
    }
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
        logInfo(data);
        if (ResponseCode.SUCCESS == data.code) {
            logInfo("获取用户信息成功.")
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
    if (isLogin()) {
        // memberView.router.navigate('/me/', {
        //     history: true
        // });
    } else {
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
    return true;
    /** 暂时屏蔽，不进行登录 */
    // if(typeof(userInfo)==="undefined" || typeof(userInfo.userId)==="undefined"){
    //     return false ;
    // } else {
    //     return true ;
    // }
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
        logInfo(data);
        if (ResponseCode.SUCCESS == data.code) {
            logInfo("删除消息成功.")
        }
    });
}


/**
 * 标记已读消息
 * @param messageId
 */
function markSystemMessage(messageId) {
    if (isLogin()) {
        var bizContent = {messageId: messageId};
        var params = {
            method: InterFace.MESSAGE_MARK,
            userId: userInfo.userId,
            bizContent: JSON.stringify(bizContent)
        };
        //标记消息已读
        app.request.json(INTERFACE_URL, params, function (data) {
            logInfo(data);
            if (ResponseCode.SUCCESS == data.code) {
                document.getElementById("messageId-" + messageId).className = " item-title color-gray ";
                logInfo("标记已读消息成功.")
            }
        });
    } else {
        //去登录
        gotoLogin();
    }


}

/**
 * 爱补贴
 */
function loveBuTieClick() {
    if (isLogin()) {
        if (userInfo.userLevel >= 2) {
            memberView.router.navigate('/lovebutie/', {
                history: true
            });
        } else {
            memberView.router.navigate('/member_rk/', {
                history: true
            });
        }
    } else {
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
    if (isLogin()) {
        if (userInfo.userLevel >= 1) {
            memberView.router.navigate('/jiankangka/', {
                history: true
            });
        } else {
            memberView.router.navigate('/member_rk/', {
                history: true
            });
        }
    } else {
        gotoLogin();
    }

}


/**
 * 转账
 */
function zhuanzhangClick() {
    if (isLogin()) {
        if (userInfo.card && userInfo.userLevel >= 1) {
            memberView.router.navigate('/zhuanzhang/', {
                history: true
            });
        } else {
            memberView.router.navigate('/bankcard/', {
                history: true
            });
        }
    } else {
        gotoLogin();
    }
}

/**
 * 申请升级
 * @param form
 */
function appLevel(form) {
    var bizContent = app.form.convertToData('#' + form);
    var params = {
        method: InterFace.APPLY_LEVEL,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };
    app.request.post(INTERFACE_URL, params, function (data) {
        logInfo(data);
        var response = JSON.parse(data);
        if (ResponseCode.SUCCESS == response.code) {
            app.dialog.alert('申请成功，请等待审核', '信息提示', function () {
                memberView.router.navigate('/?userId='+userInfo.userId, {
                    history: true
                });
            });

        } else {
            app.dialog.alert(getErrorMessage(response.code), '信息提示');
        }
    });
}

/**
 * 保险详单
 */
function baoxianxiangdanClick(insuranceOrderId) {
    if (isLogin()) {
        if (userInfo.userLevel >= 1) {
            memberView.router.navigate('/baoxianxiangdan/?insuranceOrderId=' + insuranceOrderId, {
                history: true
            });
        }
    } else {
        gotoLogin();
    }
}

/**
 * 绑定银行卡
 * @param form
 */
function bindBankCard(form) {
    var bizContent = app.form.convertToData('#' + form);
    var params = {
        method: InterFace.BIND_BANK_CARD,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };
    app.request.post(INTERFACE_URL, params, function (data) {
        logInfo(data);
        var response = JSON.parse(data);
        if (ResponseCode.SUCCESS == response.code) {
            app.dialog.alert('绑卡成功', '信息提示', function () {
                memberView.router.navigate('/zhuanzhang/', {
                    history: true
                });
            });

        } else {
            app.dialog.alert(getErrorMessage(response.code), '信息提示');
        }
    });
}

/**
 * 提现申请
 * @param form
 */
function applyWithDraw(form) {
    var bizContent = app.form.convertToData('#' + form);
    var params = {
        method: InterFace.WITHDRAW_APPLY,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };
    app.request.post(INTERFACE_URL, params, function (data) {
        logInfo(data);
        var response = JSON.parse(data);
        if (ResponseCode.SUCCESS == response.code) {
            app.dialog.alert('申请已提交成功，请等待后台审核，注意接收消息提醒。', '信息提示', function () {
                memberView.router.navigate('/lovebutie/', {
                    history: true
                });
            });
        } else {
            app.dialog.alert(getErrorMessage(response.code), '信息提示');
        }
    });
}

/** 支付弹出层 */
var dynamicPopup = undefined;

function closeDynamicPopup() {
    dynamicPopup.close();
}

/**
 * 增加购买个数
 */
function addNumber(lableName) {
    var totalNumber = 50;
    var number = document.getElementById(lableName).innerText;
    number = parseInt(number);
    if (number >= totalNumber) {
        app.dialog.alert("购买数量不能超过" + totalNumber, '信息提示');
        return false;
    }
    number++;
    document.getElementById(lableName).innerText = " " + number + " ";
    // 总计
    document.getElementById(lableName + "_all").innerText = " " + (number * 365) + " ";

    // 总计
    var numberTotal = document.getElementById(lableName + "_total").innerText;
    numberTotal = parseInt(numberTotal);
    document.getElementById(lableName + "_total").innerText = " " + (number * 365) + " ";
}

/**
 * 减购买个数
 */
function delNumber(lableName) {
    var number = document.getElementById(lableName).innerText;
    number = parseInt(number);
    if (number <= 1) {
        app.dialog.alert("购买数量不能低于1", '信息提示');
        return false;
    }
    number--;
    document.getElementById(lableName).innerText = " " + number + " ";
    // 总计
    var numberAll = document.getElementById(lableName + "_all").innerText;
    document.getElementById(lableName + "_all").innerText = " " + (number * 365) + " ";

    // 总计
    var numberTotal = document.getElementById(lableName + "_total").innerText;
    numberTotal = parseInt(numberTotal);
    document.getElementById(lableName + "_total").innerText = " " + (number * 365) + " ";
}

/**
 * 买卡提交
 * @param form
 * @param lableName
 * @returns {boolean}
 */
function submitBuyCard(form, lableName) {
    var mobile = "";
    var address = "";
    $$(dynamicPopup.el).find('input[type=text]').each(function (index, element) {
        if (element.name == "mobile") {
            mobile = element.value;
        }
        if (element.name == "address") {
            address = element.value;
        }
    });
    if (mobile == "" || address == "" || mobile.length < 11) {
        app.dialog.alert("输入信息有误，请从新输入", '信息提示');
        return false;
    }
    var number = document.getElementById(lableName).innerText;
    app.form.fillFromData('#' + form, {
        'number': number,
        'contactsMobile': mobile,
        'address': address
    })
    var bizContent = app.form.convertToData('#' + form);
    var params = {
        method: InterFace.PAY_UNIFIED_ORDER,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };

    app.request.post(INTERFACE_URL, params, function (data) {
        logInfo(data);
        var response = JSON.parse(data);
        if (ResponseCode.SUCCESS == response.code) {
            var bizContent = JSON.parse(response.bizContent);
            WeixinJSBridge.invoke('getBrandWCPayRequest', {
                "appId": bizContent.appId, //公众号名称，由商户传入
                "timeStamp": bizContent.timeStamp, //时间戳，自1970年以来的秒数
                "nonceStr": bizContent.nonceStr, //随机串
                "package": "prepay_id=" + bizContent.packageValue,
                "signType": bizContent.signType, //微信签名方式：
                "paySign": bizContent.paySign
                //微信签名
            }, function (res) {
                if (res.err_msg == "get_brand_wcpay_request:ok") {
                    closeDynamicPopup();
                    location.href=goIndex();
                } // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                else {
                    $api.rmStorage('orderData');
                    history.back(-1);
                }
            });
        } else {
            app.dialog.alert(response.msg, '信息提示');
        }
    });

}

/**
 * 预下单
 * @param form
 */
function buyCard(form) {
    if (typeof WeixinJSBridge == "undefined") {
        app.dialog.alert("请在微信端内进行操作!", '信息提示');
        return false;
    }
    dynamicPopup = app.popup.create({
        content: '<div class="popup" style="height: 50%">' +
        '<div class="block-title">请填写您的收获地址与联系方式</div>\n' +
        '<div class="list inset">\n' +
        '  <ul>\n' +
        '    <li class="item-content item-input">\n' +
        '      <div class="item-inner">\n' +
        '        <div class="item-input-wrap">\n' +
        '          <input type="text" name="mobile" placeholder="您的手机号码" maxlength="20">\n' +
        '          <span class="input-clear-button"></span>\n' +
        '        </div>\n' +
        '      </div>\n' +
        '    </li>\n' +
        '    <li class="item-content item-input">\n' +
        '      <div class="item-inner">\n' +
        '        <div class="item-input-wrap">\n' +
        '          <input type="text" name="address" placeholder="收获地址" maxlength="120">\n' +
        '          <span class="input-clear-button"></span>\n' +
        '        </div>\n' +
        '      </div>\n' +
        '    </li>\n' +
        '  </ul>\n' +
        '<div class="row">\n' +
        '  <div class="col-50">\n' +
        '    <a href="#" class="button button-big button-fill button-raised active" onclick="closeDynamicPopup()">取消</a>\n' +
        '  </div>\n' +
        '  <div class="col-50">\n' +
        '    <a href="#" class="button button-big button-fill button-raised active" onclick="submitBuyCard(\'submitForm\',\'numberLabel\')">确定</a>\n' +
        '  </div>\n' +
        '</div>    ' +
        '</div>' +

        '</div>',
        // Events
        on: {
            open: function (popup) {
                console.log('Popup open');
            },
            opened: function (popup) {
                console.log('Popup opened');
            },
        }
    });
    dynamicPopup.open();
}

/**
 * 卖卡弹出收件信息
 * @param form
 * @returns {boolean}
 */
function sellCard(form) {
    if (typeof WeixinJSBridge == "undefined") {
        app.dialog.alert("请在微信端内进行操作!", '信息提示');
        return false;
    }
    dynamicPopup = app.popup.create({
        content: '<div class="popup" style="height: 50%">' +
        '<div class="block-title">请填写您的收获地址与联系方式</div>\n' +
        '<div class="list inset">\n' +
        '  <ul>\n' +
        '    <li class="item-content item-input">\n' +
        '      <div class="item-inner">\n' +
        '        <div class="item-input-wrap">\n' +
        '          <input type="text" name="mobile" placeholder="您的手机号码" maxlength="20">\n' +
        '          <span class="input-clear-button"></span>\n' +
        '        </div>\n' +
        '      </div>\n' +
        '    </li>\n' +
        '    <li class="item-content item-input">\n' +
        '      <div class="item-inner">\n' +
        '        <div class="item-input-wrap">\n' +
        '          <input type="text" name="address" placeholder="收获地址" maxlength="120">\n' +
        '          <span class="input-clear-button"></span>\n' +
        '        </div>\n' +
        '      </div>\n' +
        '    </li>\n' +
        '  </ul>\n' +
        '<div class="row">\n' +
        '  <div class="col-50">\n' +
        '    <a href="#" class="button button-big button-fill button-raised active" onclick="closeDynamicPopup()">取消</a>\n' +
        '  </div>\n' +
        '  <div class="col-50">\n' +
        '    <a href="#" class="button button-big button-fill button-raised active" onclick="submitSellCard(\'sellSubmitForm\')">确定</a>\n' +
        '  </div>\n' +
        '</div>    ' +
        '</div>' +

        '</div>',
        // Events
        on: {
            open: function (popup) {
                console.log('Popup open');
            },
            opened: function (popup) {
                console.log('Popup opened');
            },
        }
    });
    dynamicPopup.open();
}

/**
 * 提交卖卡数据
 * @param form
 * @param lableName
 * @returns {boolean}
 */
function submitSellCard(form) {
    var mobile = "";
    var address = "";
    $$(dynamicPopup.el).find('input[type=text]').each(function (index, element) {
        if (element.name == "mobile") {
            mobile = element.value;
        }
        if (element.name == "address") {
            address = element.value;
        }
    });
    if (mobile == "" || address == "" || mobile.length < 11) {
        app.dialog.alert("输入信息有误，请从新输入", '信息提示');
        return false;
    }
    app.form.fillFromData('#' + form, {
        'contactsMobile': mobile,
        'address': address,
        'cardNo': cardNo
    })
    var bizContent = app.form.convertToData('#' + form);
    var params = {
        method: InterFace.PAY_UNIFIED_ORDER,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };

    app.request.post(INTERFACE_URL, params, function (data) {
        logInfo(data);
        var response = JSON.parse(data);
        if (ResponseCode.SUCCESS == response.code) {
            var bizContent = JSON.parse(response.bizContent);
            WeixinJSBridge.invoke('getBrandWCPayRequest', {
                "appId": bizContent.appId, //公众号名称，由商户传入
                "timeStamp": bizContent.timeStamp, //时间戳，自1970年以来的秒数
                "nonceStr": bizContent.nonceStr, //随机串
                "package": "prepay_id=" + bizContent.packageValue,
                "signType": bizContent.signType, //微信签名方式：
                "paySign": bizContent.paySign
                //微信签名
            }, function (res) {
                if (res.err_msg == "get_brand_wcpay_request:ok") {
                    closeDynamicPopup();
                    location.href=goIndex();
                } // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                else {
                    $api.rmStorage('orderData');
                    history.back(-1);
                }
            });
        } else {
            app.dialog.alert(response.msg, '信息提示');
        }
    });
}

/**
 * 引导用户进行分享覆层
 * @type {{$: _system.$, _client: _system._client, _scroll: _system._scroll, _cover: _system._cover, _guide: _system._guide, _zero: _system._zero}}
 * @private
 */
var _system = {
    $: function (id) {
        return document.getElementById(id);
    },
    _client: function () {
        return {
            w: document.documentElement.scrollWidth,
            h: document.documentElement.scrollHeight,
            bw: document.documentElement.clientWidth,
            bh: document.documentElement.clientHeight
        };
    },
    _scroll: function () {
        return {
            x: document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft,
            y: document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop
        };
    },
    _cover: function (show) {
        if (show) {
            this.$("cover").style.display = "block";
            this.$("cover").style.width = (this._client().bw > this._client().w ? this._client().bw : this._client().w) + "px";
            this.$("cover").style.height = (this._client().bh > this._client().h ? this._client().bh : this._client().h) + "px";
        } else {
            this.$("cover").style.display = "none";
        }
    },
    _guide: function (click) {
        this._cover(true);
        this.$("guide").style.display = "block";
        this.$("guide").style.top = (_system._scroll().y + 5) + "px";
        window.onresize = function () {
            _system._cover(true);
            _system.$("guide").style.top = (_system._scroll().y + 5) + "px";
        };
        if (click) {
            _system.$("cover").onclick = function () {
                _system._cover();
                _system.$("guide").style.display = "none";
                _system.$("cover").onclick = null;
                window.onresize = null;
            };
        }
    },
    _zero: function (n) {
        return n < 0 ? 0 : n;
    }
}

/**
 * 分享微信售卡链接
 * @param cardNo
 */
function shardWeixin(cardNo) {
    var bizContent = {cardNo: cardNo};

    var params = {
        method: InterFace.WX_GLOBLE_CONFIG,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };

    app.request.post(INTERFACE_URL, params, function (data) {
        logInfo(data)
        var response = JSON.parse(data);
        if (ResponseCode.SUCCESS == response.code) {
            var bizContent = JSON.parse(response.bizContent);
            var sellUserInfo = bizContent.sellUser;
            wx.config({
                debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: bizContent.appId, // 必填，公众号的唯一标识
                timestamp: bizContent.timeStamp, // 必填，生成签名的时间戳
                nonceStr: bizContent.nonceStr, // 必填，生成签名的随机串
                signature: bizContent.signature,// 必填，签名，见附录1
                jsApiList: ['checkJsApi', 'onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ',
                    'onMenuShareQZone']
                // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
            wx.ready(function () {
                wx.onMenuShareAppMessage({
                    title: '健康卡购买', // 分享标题
                    desc: '用户'+sellUserInfo.nickName+"给您分享了他的健康卡", // 分享描述
                    link: 'http://t.kuai-kaifa.com/weixin/goOauth?backUrl=shard_sell.html&cardNo='+cardNo, // 分享链接
                    imgUrl: sellUserInfo.wxHeadPortrait, // 分享图标
                    type: '', // 分享类型,music、video或link，不填默认为link
                    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                    success: function () {
                        // 用户确认分享后执行的回调函数
                        app.dialog.alert("分享成功");
                    },
                    cancel: function () {
                        // 用户取消分享后执行的回调函数
                    }
                });
            });
            _system._guide(true);
        } else {
            app.dialog.alert(data.msg, function () {
                location.href = goIndex();
            });
        }
    });
}

/**
 * 获取异常信息
 * @param code
 */
function getErrorMessage(code) {
    if (ErrorMessage.containsKey(code)) {
        return ErrorMessage.get(code);
    }
    return "操作异常";
}

/**
 * 游客申请会员
 */
function appylyMember() {
    if (isLogin()) {
            memberView.router.navigate('/buyCard/', {
                history: true
            });
    } else {
        gotoLogin();
    }
}