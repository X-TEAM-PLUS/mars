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
function loadUserView(token) {
    var params = {
        method: InterFace.USER_INFO, token: token
    };
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
        } else {
            gotoLogin();
        }
    });
}

/**
 * 展示理事升级的team信息
 */
function showLishiTeamView() {
    if (isLogin()) {
        memberView.router.navigate('/lishiLevelView/', {
            history: true
        });
    } else {
        //去登录
        gotoLogin();
    }
}

/**
 * 展示常任理事升级的team信息
 */
function showChangrenLishiTeamView() {
    if (isLogin()) {
        memberView.router.navigate('/changrenlishiLevelView/', {
            history: true
        });
    } else {
        //去登录
        gotoLogin();
    }
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
    if (localStorage.hasOwnProperty(TOKEN)) {
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
        } else if (userInfo.userLevel == 1){
            memberView.router.navigate('/appLevel/', {
                history: true
            });
        }else {
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
 * 我的TEAM
 */
function myTeam() {
    if (isLogin()) {
        if (userInfo.userLevel >= 1) {
            memberView.router.navigate('/my_team/', {
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
 * 申请成为社工
 * @param form
 */
function appLevel(form) {
    var bizContent = app.form.convertToData('#' + form);
    var params = {
        method: InterFace.APPLY_LEVEL_SOCIAL,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };
    app.request.post(INTERFACE_URL, params, function (data) {
        logInfo(data);
        var response = JSON.parse(data);
        if (ResponseCode.SUCCESS == response.code) {
            app.dialog.alert('申请成功，请等待审核', '信息提示', function () {
                memberView.router.navigate('/?userId=' + userInfo.userId, {
                    history: true
                });
            });

        } else {
            app.dialog.alert(response.msg, '信息提示');
        }
    });
}

/**
 * 申请成为理事
 */
function appLevelDirector(wayType) {
    var bizContent ={
        wayType:wayType
    };
    var params = {
        method: InterFace.APPLY_LEVEL_DIRECTOR,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };
    app.request.post(INTERFACE_URL, params, function (data) {
        logInfo(data);
        var response = JSON.parse(data);
        if (ResponseCode.SUCCESS == response.code) {
            app.dialog.alert('申请成功，请保持手机畅通,稍后客服会直接联系您。', '信息提示', function () {
                if(2==wayType){
                    app.popup.close('.lishi-popup',true);
                }
                memberView.router.navigate('/?userId=' + userInfo.userId, {
                    history: true
                });
            });

        } else {
            app.dialog.alert(response.msg, '信息提示');
        }
    });
}

/**
 * 申请成为常任理事
 */
function appLevelStandingDirector(wayType) {
    var bizContent ={
        wayType:wayType
    };
    var params = {
        method: InterFace.APPLY_LEVEL_STANDING_DIRECTOR,
        userId: userInfo.userId,
        bizContent: JSON.stringify(bizContent)
    };
    app.request.post(INTERFACE_URL, params, function (data) {
        logInfo(data);
        var response = JSON.parse(data);
        if (ResponseCode.SUCCESS == response.code) {
            app.dialog.alert('申请成功，请等待审核', '信息提示', function () {
                if(2==wayType){
                    app.popup.close('.lishi-popup',true);
                }
                memberView.router.navigate('/?userId=' + userInfo.userId, {
                    history: true
                });
            });
        } else {
            app.dialog.alert(response.msg, '信息提示');
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
            app.dialog.alert(response.msg, '信息提示');
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
            app.dialog.alert(response.msg, '信息提示');
        }
    });
}


/**
 * 增加购买个数
 */
function addNumber(lableName) {
    // var totalNumber = 50;
    // var number = document.getElementById(lableName).innerText;
    // number = parseInt(number);
    // if (number >= totalNumber) {
    //     app.dialog.alert("购买数量不能超过" + totalNumber, '信息提示');
    //     return false;
    // }
    // number++;
    // document.getElementById(lableName).innerText = " " + number + " ";
    // // 总计
    // document.getElementById(lableName + "_all").innerText = " " + (number * 365) + " ";
    //
    // // 总计
    // var numberTotal = document.getElementById(lableName + "_total").innerText;
    // numberTotal = parseInt(numberTotal);
    // document.getElementById(lableName + "_total").innerText = " " + (number * 365) + " ";
}

/**
 * 减购买个数
 */
function delNumber(lableName) {
    // var number = document.getElementById(lableName).innerText;
    // number = parseInt(number);
    // if (number <= 1) {
    //     app.dialog.alert("购买数量不能低于1", '信息提示');
    //     return false;
    // }
    // number--;
    // document.getElementById(lableName).innerText = " " + number + " ";
    // // 总计
    // var numberAll = document.getElementById(lableName + "_all").innerText;
    // document.getElementById(lableName + "_all").innerText = " " + (number * 365) + " ";
    //
    // // 总计
    // var numberTotal = document.getElementById(lableName + "_total").innerText;
    // numberTotal = parseInt(numberTotal);
    // document.getElementById(lableName + "_total").innerText = " " + (number * 365) + " ";
}

/**
 * 买卡提交
 * @param form
 * @param lableName
 * @returns {boolean}
 */
function submitBuyCard(form) {
    let bizContent = app.form.convertToData('#' + form);
    logInfo(bizContent);
    if (userInfo.userLevel == 0) {
        if (!bizContent.userRealName) {
            app.dialog.alert('请输入您的真实姓名', '信息提示');
            return false;
        }
        if (!bizContent.certificateOf) {
            app.dialog.alert('请输入您的身份证号', '信息提示');
            return false;
        }
        if (!bizContent.address) {
            app.dialog.alert('请输入您的收货地址', '信息提示');
            return false;
        }
        //如果有指定的卡号
        if (sessionStorage.hasOwnProperty("cardNo")){
            bizContent["cardNo"] = sessionStorage.getItem("cardNo");
            bizContent["orderTypeEnum"]="VIP_DISTRIBUTION";
        }else{
            bizContent["orderTypeEnum"]="PLATFORM_STRAIGHT";
        }
    }else{
        bizContent["orderTypeEnum"]="PLATFORM_STRAIGHT";
    }

    let  params = {
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
                    app.popup.close('.orderFormPopup', true);
                    memberView.router.navigate('/', {
                        history: true
                    });
                } // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                else {
                    //$api.rmStorage('orderData');
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
function buyCard() {
    if (typeof WeixinJSBridge == "undefined") {
        app.dialog.alert("请在微信端内进行操作!", '信息提示');
        return false;
    }
    if (isLogin()) {
        if (userInfo.userLevel == 0) {
            app.popup.open('.orderFormPopup', true);
        }else{
            submitBuyCard('orderForm');
        }
    } else {
        gotoLogin();
    }
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
    if (isLogin()) {
        if (userInfo.userLevel >= 1) {
            shardContentApi(
                window.location.protocol+"//"+window.location.host+"/index.html?cardNo="+cardNo
                ,userInfo.wxHeadPortrait
                ,"健康卡购买"
                ,userInfo.realName  +"给您分享了他的健康卡"
            );
            logInfo("分享微信售卡链接");
        }
    } else {
        gotoLogin();
    }

}

function obj2string(o) {
    var r = [];
    if (typeof o == "string") {
        return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
    }
    if (typeof o == "object") {
        if (!o.sort) {
            for (var i in o) {
                r.push(i + ":" + obj2string(o[i]));
            }
            if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
                r.push("toString:" + o.toString.toString());
            }
            r = "{" + r.join() + "}";
        } else {
            for (var i = 0; i < o.length; i++) {
                r.push(obj2string(o[i]))
            }
            r = "[" + r.join() + "]";
        }
        return r;
    }
    return o.toString();
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

// 判断是否为手机号
function isPoneAvailable(pone) {
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(pone)) {
        return false;
    } else {
        return true;
    }
}

/**
 * 获取短信验证码
 * @param form
 */
function getSmsCode(form) {
    let bizContent = app.form.convertToData('#' + form);
    if (typeof(bizContent) === "undefined" || typeof(bizContent.mobileNo) === "undefined") {
        app.dialog.alert("请输入手机号", '信息提示');
    } else if (isPoneAvailable(bizContent.mobileNo)) {
        if (SMS_BUTTON_WAIT == 60) {
            time();
            //发送短信验证码
            sendSmsCode(bizContent.mobileNo);
        }
    } else {
        app.dialog.alert("手机号输入不正确", '信息提示');
    }
}

function time() {
    logInfo(SMS_BUTTON_WAIT);
    if (SMS_BUTTON_WAIT == 0) {
        $$(".getSmsCodeButton").html("获取验证码");
        SMS_BUTTON_WAIT = 60;
    } else {
        $$(".getSmsCodeButton").html(SMS_BUTTON_WAIT+"秒后可以重新发送");
        SMS_BUTTON_WAIT--;
        setTimeout(function() {
                time()
            },
            1000)
    }
}

/**
 * 发送短信验证码
 * @param mobileNo
 */
function sendSmsCode(mobileNo) {
    var params = {
        method: InterFace.GET_SMS_CODE,
        bizContent: JSON.stringify({
            mobileNo: mobileNo
        })
    };
    app.request.post(INTERFACE_URL, params, function (data) {
        logInfo(data);
        let response = JSON.parse(data);
        if (ResponseCode.SUCCESS === response.code) {
            app.dialog.alert('短信验证码发送成功，请注意查收', '信息提示');
        } else {
            app.dialog.alert(response.msg, '信息提示');
        }
    });
}

/**
 * 登录
 * @param form
 */
function goLogin(form) {
    let bizContent = app.form.convertToData('#' + form);
    if (localStorage.hasOwnProperty('employeeCardNo')) {
        bizContent["employeeCardNo"]= localStorage.getItem("employeeCardNo");
    }
    if (typeof(bizContent) === "undefined" || typeof(bizContent.mobileNo) === "undefined" || typeof(bizContent.smsCode) === "undefined") {
        app.dialog.alert("请输入手机号和短信验证码", '信息提示');
    } else if (isPoneAvailable(bizContent.mobileNo)) {
        doLogin(bizContent);
    } else {
        app.dialog.alert("手机号输入不正确", '信息提示');
    }
}

/**
 * 登录
 */
function doLogin(formData) {
    let params = {
        method: InterFace.SMS_LOGIN,
        bizContent: JSON.stringify(formData)
    };
    logInfo(params);
    app.request.post(INTERFACE_URL, params, function (data) {
        let response = JSON.parse(data);
        if (ResponseCode.SUCCESS == response.code) {
            let bizContent = JSON.parse(response.bizContent);
            localStorage.setItem(TOKEN, bizContent.token);
            //移除推荐人
            localStorage.removeItem("employeeCardNo");

            // 微信端内进行跳转
            if (typeof WeixinJSBridge != "undefined") {
                if (cardNo != undefined && cardNo != "") {
                    location.href =   "/webservice/weixin/goOauth?backUrl=shard_sell.html&cipherTxt=" + bizContent.token + "&cardNo=" + cardNo;
                } else {
                    location.href =  "/webservice/weixin/goOauth?backUrl=index.html&cipherTxt=" + bizContent.token;
                }
            } else {
                memberView.router.navigate('/', {
                    history: true
                });
            }
        } else {
            app.dialog.alert(response.msg, '信息提示');
        }
    });
}

/**
 * 任务达成
 */
function taskSuccess() {
    document.getElementById("isDirectReach").innerText = "已达成";
    $$('.buyCard-lishi').attr('style', 'visibility:hidden');
    $$('.appy-lishi').attr('style', 'visibility: visible');
}

/**
 * 显示社工销售信息
 * @param token
 */
function viewMarketingInformation(token) {
    var params = {
        method: InterFace.TEAM_COUNT, token: token
    };
    app.request.json(INTERFACE_URL, params, function (data) {
        var bizContent = JSON.parse(data.bizContent);
        logInfo(bizContent);
        document.getElementById("userLevel").innerText = bizContent.userLevel;
        document.getElementById("directReachCount").innerText = bizContent.refereeUserCount + "/"+PerformanceIndex.DIRECTOR;
        if (bizContent.refereeUserCount == PerformanceIndex.DIRECTOR) {
            taskSuccess();
        }
        app.progressbar.set('#directReachProgressbar1', bizContent.refereeUserCount * 100.0 / PerformanceIndex.DIRECTOR);

        if (bizContent.userTeamList.length > 0) {
            document.getElementById("indirectReachCount1").innerText = bizContent.userTeamList[0].userCount + "/"+PerformanceIndex.DIRECTOR;
            app.progressbar.set('#indirectReachProgressbar1', bizContent.userTeamList[0].userCount * 100.0 / PerformanceIndex.DIRECTOR);
        }
        if (bizContent.userTeamList.length > 1) {
            document.getElementById("indirectReachCount2").innerText = bizContent.userTeamList[1].userCount + "/500";
            if (bizContent.userTeamList[0].userCount == PerformanceIndex.DIRECTOR && bizContent.userTeamList[1].userCount == PerformanceIndex.DIRECTOR) {
                taskSuccess();
            }
            app.progressbar.set('#indirectReachProgressbar2', bizContent.userTeamList[1].userCount * 100.0 / PerformanceIndex.DIRECTOR);
        }
    });
}

/**
 * 显示理事销售信息
 * @param token
 */
function viewMarketingInformationByLishi(token) {
    var params = {
        method: InterFace.TEAM_COUNT, token: token
    };
    app.request.json(INTERFACE_URL, params, function (data) {
        var bizContent = JSON.parse(data.bizContent);
        logInfo(bizContent);
        document.getElementById("userLevel").innerText = bizContent.userLevel;
        document.getElementById("directReachCount").innerText = bizContent.refereeUserCount + "/"+PerformanceIndex.STANDING_DIRECTOR;
        if (bizContent.refereeUserCount == PerformanceIndex.STANDING_DIRECTOR) {
            taskSuccess();
        }
        app.progressbar.set('#directReachProgressbar1', bizContent.refereeUserCount * 100.0 / PerformanceIndex.STANDING_DIRECTOR);

        if (bizContent.userTeamList.length > 0) {
            document.getElementById("indirectReachCount1").innerText = bizContent.userTeamList[0].userCount + "/"+PerformanceIndex.STANDING_DIRECTOR;
            app.progressbar.set('#indirectReachProgressbar1', bizContent.userTeamList[0].userCount * 100.0 / PerformanceIndex.STANDING_DIRECTOR);
        }
        if (bizContent.userTeamList.length > 1) {
            document.getElementById("indirectReachCount2").innerText = bizContent.userTeamList[1].userCount + "/"+PerformanceIndex.STANDING_DIRECTOR;
            if (bizContent.userTeamList[0].userCount == PerformanceIndex.STANDING_DIRECTOR && bizContent.userTeamList[1].userCount == PerformanceIndex.STANDING_DIRECTOR) {
                taskSuccess();
            }
            app.progressbar.set('#indirectReachProgressbar2', bizContent.userTeamList[1].userCount * 100.0 /PerformanceIndex.STANDING_DIRECTOR);
        }
    });
}

/**
 * 我的推广码
 */
function meTuiGuangMa() {
    if (isLogin()) {
        if (userInfo.userLevel >= 1) {
            memberView.router.navigate('/employeeCard/', {
                history: true
            });
        }
    } else {
        gotoLogin();
    }
}


/**
 * 获取社工证信息
 * @param token
 */
function getEmployeeCard(token) {
    let params = {
        method: InterFace.GET_QR_CODE, token: token
    };
    app.request.json(INTERFACE_URL, params, function (data) {
        logInfo(data);
        if(data.bizContent){
            let bizContent = JSON.parse(data.bizContent);
            logInfo(bizContent);
            if(bizContent.employeeCard){
                $$('#employeeCard')[0].src = bizContent.employeeCard;
                $$('#downloadPic')[0].href = bizContent.employeeCard;
            }

        }
    });
}

/**
 * 保存社工证
 */
function saveEmployeeCard() {
    if (isLogin()) {
        if (userInfo.userLevel >= 1) {
           //TODO 下载图片
            logInfo("保存社工证");
        }
    } else {
        gotoLogin();
    }
}

/**
 * 分享社工证
 */
function shareEmployeeCard(){
    if (isLogin()) {
        if (userInfo.userLevel >= 1) {
            shardContentApi(
                window.location.protocol+"//"+window.location.host+"/index.html?employeeCardNo="+userInfo.userId
            ,window.location.protocol+"//"+window.location.host+"/"+userInfo.employeeCard
            ,"我的推广码"
            ,userInfo.realName  +"分享了早安工程社工证"
            );
            logInfo("分享社工证");
        }
    } else {
        gotoLogin();
    }
}

/**
 * 提交检查结果
 * @param form
 */
function submitCheckResult(form) {
    var formData = new FormData(document.forms.namedItem(form));
    formData.append("method",InterFace.SUBMIT_CHECK_RESULT);
    formData.append("userId",userInfo.userId);
    var bizContent = app.form.convertToData('#' + form);
    formData.append("bizContent",JSON.stringify(bizContent));
    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("POST",INTERFACE_URL,true);
    xmlHttpRequest.onreadystatechange = function () {
        if (xmlHttpRequest.readyState == 4) {
            if (xmlHttpRequest.status == 200) {
                var responseText = JSON.parse(xmlHttpRequest.responseText);
                if(ResponseCode.SUCCESS==responseText.code){
                    app.dialog.alert('检查结果上传成功。', '信息提示',function () {
                        heartCheckView.router.navigate('/heart_check/', {
                            history: true
                        });
                    });
                }else{
                    app.dialog.alert('检查结果上传失败。', '信息提示');
                }
            } else {
                // 错误处理
                app.dialog.alert('检查结果上传失败。', '信息提示');
            }
        }
    }
    xmlHttpRequest.send(formData);
}

/**
 * 预览图片
 */
function previewPic(checkResultFile,previewPicBox){
    var  picFile = $$('#'+checkResultFile)[0].files[0];
    var reader = new FileReader();
    reader.onload = function (e) {
        $$('#'+previewPicBox)[0].src = e.target.result;
    }
    if(picFile){
        reader.readAsDataURL(picFile);
        $$('#'+previewPicBox).attr('style', 'visibility: visible;height: 137px;width: 248px');
    }
}

/**
 * 查询检查结果
 */
function queryCheckResult() {
    if (isLogin()) {
        if (userInfo.userLevel >= 1) {
            heartCheckView.router.navigate('/jiankangka/', {
                history: true
            });
        }
    } else {
        gotoLogin();
    }
}

/**
 * 获取最后检查的记录信息
 */
function getLastCheckInfo(token) {
    var params = {
        method: InterFace.GET_LAST_CHECK_RECORD, token: token
    };
    app.request.json(INTERFACE_URL, params, function (data) {
        if(data.bizContent){
            var bizContent = JSON.parse(data.bizContent);
            logInfo(bizContent);
            document.getElementById("lastCheckTime").innerText ="您于"+ bizContent.uploadTime + "进行体检";
        }
    });
}


/**
 * 分享微信链接
 * @param cardNo
 */
function shardContentApi(url,icon,title,desc) {
    var bizContent = {cardNo: cardNo};

    var params = {
        method: InterFace.WX_GLOBLE_CONFIG,
        userId: userInfo.userId,
        requestUrl:window.location.href,
        bizContent: JSON.stringify(bizContent)
    };

    app.request.post(INTERFACE_URL, params, function (data) {
        logInfo(data)
        var response = JSON.parse(data);
        if (ResponseCode.SUCCESS == response.code) {
            var bizContent = JSON.parse(response.bizContent);
            wx.config({
                debug: false,
                appId: bizContent.appId, // 必填，公众号的唯一标识
                timestamp: bizContent.timeStamp, // 必填，生成签名的时间戳
                nonceStr: bizContent.nonceStr, // 必填，生成签名的随机串
                signature: bizContent.signature,// 必填，签名，见附录1
                jsApiList: [
                    'checkJsApi',
                    'onMenuShareTimeline',
                    'onMenuShareAppMessage',
                    'onMenuShareQQ',
                    'onMenuShareWeibo',
                    'onMenuShareQZone',
                    'hideMenuItems',
                    'showMenuItems',
                    'hideAllNonBaseMenuItem',
                    'showAllNonBaseMenuItem',
                    'translateVoice',
                    'startRecord',
                    'stopRecord',
                    'onVoiceRecordEnd',
                    'playVoice',
                    'onVoicePlayEnd',
                    'pauseVoice',
                    'stopVoice',
                    'uploadVoice',
                    'downloadVoice',
                    'chooseImage',
                    'previewImage',
                    'uploadImage',
                    'downloadImage',
                    'getNetworkType',
                    'openLocation',
                    'getLocation',
                    'hideOptionMenu',
                    'showOptionMenu',
                    'closeWindow',
                    'scanQRCode',
                    'chooseWXPay',
                    'openProductSpecificView',
                    'addCard',
                    'chooseCard',
                    'openCard'
                ]
            });


            wx.ready(function () {
                wx.onMenuShareAppMessage({
                    title: title, // 分享标题
                    desc:desc, // 分享描述
                    link: url, // 分享链接
                    imgUrl: icon, // 分享图标
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

                _system._guide(true);
            });

            wx.error(function (res) {
                app.dialog.alert("res : " + obj2string(res));

            });

        } else {
            app.dialog.alert(data.msg, function () {
                location.href = goIndex();
            });
        }
    });
}

