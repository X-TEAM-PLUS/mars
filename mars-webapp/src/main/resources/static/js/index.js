/**
 * 初始化页面
 */
var app = new Framework7({
    id: 'www.mars.com'
    , root: '#app'
    , routes: routes
    , dynamicNavbar: true
    , dialog: {
        // set default title for all dialog shortcuts
        title: '信息提示',
        // change default "OK" button text
        buttonOk: '确定',
    }
});

$$(document).on('page:init popupOpen', '.page[data-name="home"]', function (e) {
    //获取用户信息
    if (localStorage.hasOwnProperty(TOKEN)) {
        loadUserView(localStorage.getItem(TOKEN));
    } else {
        app.dialog.alert("您还没有登录，等先进行登录!", function () {
            gotoLogin();
        });
    }
});
$$(document).on('page:init', '.page[data-name="sellCard-page"]', function (e) {

    var page = e.detail.route;
    if (page != undefined && page.query != undefined && page.query.cardNo != undefined) {
        cardNo = page.query.cardNo;
    }
    if (localStorage.hasOwnProperty(TOKEN)) {
        var params = {
            method: InterFace.USER_INFO, token: localStorage.getItem(TOKEN)
        };
        //获取用户信息
        app.request.json(INTERFACE_URL, params, function (data) {
            logInfo(data);
            if (ResponseCode.SUCCESS == data.code) {
                logInfo("获取用户信息成功.")
                var bizContent = JSON.parse(data.bizContent);
                setUserInfo(bizContent);
                loadData(userInfo, "sell-userinfo", "show-sell-userinfo");
                // 获取用户健康卡详情与购卡人信息
                var bizContent = {cardNo: cardNo};
                params = {
                    method: InterFace.USER_HEALTH_CARD_DETAIL,
                    bizContent: JSON.stringify(bizContent), token: localStorage.getItem(TOKEN)
                }
                app.request.json(INTERFACE_URL, params, function (dataHealth) {
                    logInfo(dataHealth);
                    if (ResponseCode.SUCCESS == dataHealth.code) {
                        logInfo("获取用户健康卡详情与购卡人信息成功")
                        var bizContentHealth = JSON.parse(dataHealth.bizContent);
                        loadData(bizContentHealth, "card-userinfo", "show-card-userinfo");
                    } else {
                        app.dialog.alert(dataHealth.msg, function () {
                            gotoLogin();
                        });
                    }
                });
            } else {
                app.dialog.alert(data.msg, function () {
                    gotoLogin();
                });
            }
        });
    } else {
        app.dialog.alert("您还没有登录，等先进行登录!", function () {
            gotoLogin();
        });

    }
});
$$(document).on('page:reinit', '.page[data-name="home"]', function (e) {
    //获取用户信
    if (localStorage.hasOwnProperty(TOKEN)) {
        loadUserView(localStorage.getItem(TOKEN));
    } else {
        gotoLogin();
    }

});
$$(document).on('page:init', '.page[data-name="buyCard-page"]', function (e) {
    loadData(userInfo, "buy-userinfo", "show-buy-userinfo");
});
$$(document).on('page:init', '.page[data-name="question-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {method: InterFace.QUESTION_LIST}, "question-list", "show-question-list");
});
$$(document).on('page:init', '.page[data-name="message-page"]', function (e) {
    if (localStorage.hasOwnProperty(TOKEN)) {
        loadBizContent(INTERFACE_URL, {
            method: InterFace.MESSAGE_LIST,
            token: localStorage.getItem(TOKEN)
        }, "message-list", "show-message-list");
    }
});
$$(document).on('page:init', '.page[data-name="account-info-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.ACCOUNT_INFO,
        token: localStorage.getItem(TOKEN)
    }, "account-info", "show-account-info");
});
$$(document).on('page:init', '.page[data-name="jiankangka-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.ME_HEART_CARD_INFO,
        token: localStorage.getItem(TOKEN)
    }, "jiankangka-info", "show-jiankangka-info");
});
$$(document).on('page:init', '.page[data-name="insurance-product-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {method: InterFace.INSURANCE_LIST}, "insurance-product-list", "show-insurance-product-list");
});
$$(document).on('page:init', '.page[data-name="heart-card-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.USER_HEALTH_CARD_LIST,
        token: localStorage.getItem(TOKEN)
    }, "heart-card-list", "show-heart-card-list");
});
$$(document).on('page:init', '.page[data-name="lishi-level-user-view"]', function (e) {
    viewMarketingInformation(localStorage.getItem(TOKEN));
});
$$(document).on('page:init', '.page[data-name="changrenlishi-level-user-view"]', function (e) {
    viewMarketingInformationByLishi(localStorage.getItem(TOKEN));
});
$$(document).on('page:init', '.page[data-name="wode-baoxian-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.USER_INSURANCE_LIST,
        token: localStorage.getItem(TOKEN)
    }, "wode-baoxian-list", "show-wode-baoxian-list");
});
$$(document).on('page:init', '.page[data-name="wode-baoxian-detail-page"]', function (e) {
    var bizContent = {insuranceOrderId: e.detail.route.query.insuranceOrderId};
    loadBizContent(INTERFACE_URL, {
        method: InterFace.USER_INSURANCE_DETAIL,
        token: localStorage.getItem(TOKEN),
        bizContent: JSON.stringify(bizContent)
    }, "wode-baoxian-detail", "show-wode-baoxian-detail");
});
$$(document).on('page:init', '.page[data-name="bankcard-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {method: InterFace.BANK_LIST}, "bank-list", "show-bank-list");
});
$$(document).on('page:init', '.page[data-name="zhuanzhang-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.USER_WITHDRAW_RECORD_LIST,
        token: localStorage.getItem(TOKEN)
    }, "zhuanzhang-list", "show-zhuanzhang-list");
});
$$(document).on('page:init', '.page[data-name="butiemingxi-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.USER_SUBSIDY_LIST,
        token: localStorage.getItem(TOKEN)
    }, "butiemingxi-list", "show-butiemingxi-list");
});
$$(document).on('page:init', '.page[data-name="zhuanzhang-form-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.ACCOUNT_AND_BANK_CARD,
        token: localStorage.getItem(TOKEN)
    }, "zhuanzhang-form", "show-zhuanzhang-form");
});
$$(document).on('page:init', '.page[data-name="show-me-page"]', function (e) {
    loadBizContent(userInfo, "show-me", "show-me-view");
});

$$(document).on('page:init', '.page[data-name="heartcheck-page"]', function (e) {
    if (localStorage.hasOwnProperty(TOKEN)) {
        getLastCheckInfo(localStorage.getItem(TOKEN));
    }
});
app.views.create('#view-college', {url: '/college/'});
var memberView = app.views.create('#view-member', {url: '/'});
var heartCheckView = app.views.create('#view-heart_check', {url: '/heart_check/'});

