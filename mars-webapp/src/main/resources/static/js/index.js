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
        buttonCancel:'取消'
    }
});

$$(document).on('page:init reinit popupOpen', '.page[data-name="home"]', function (e) {
    let pageParams = e.detail.route.query;

    if(pageParams.cardNo){
        logInfo("准备购买分享的健康卡的卡号为::"+pageParams.cardNo);
        sessionStorage.setItem("cardNo",pageParams.cardNo);
    }
    //获取用户信息
    if (sessionStorage.hasOwnProperty(TOKEN)) {
        loadUserView(sessionStorage.getItem(TOKEN),pageParams);
    } else {
            if(pageParams.employeeCardNo){
                //获取用户信息
                app.request.json(INTERFACE_URL, {
                    method: InterFace.USER_INFO, userId: pageParams.employeeCardNo
                }, function (data) {
                    logInfo(data);
                    if (ResponseCode.SUCCESS == data.code) {
                        logInfo("获取用户信息成功.")
                        var employeeUserInfo = JSON.parse(data.bizContent);
                        if(sessionStorage.hasOwnProperty("employeeCardNo") && sessionStorage.getItem("employeeCardNo")==pageParams.employeeCardNo){
                            logInfo("已设置相同推存人："+pageParams.employeeCardNo);
                        }else{
                            app.dialog.confirm("您确认接受 【"+ employeeUserInfo.realName +"】的邀请加入早安工程吗", "信息提示", function () {
                                logInfo("接收推荐人:"+pageParams.employeeCardNo);
                                sessionStorage.setItem("employeeCardNo",pageParams.employeeCardNo);
                            }, function () {
                                logInfo("未接收推荐人:"+pageParams.employeeCardNo);
                            });
                        }
                    }else{
                        logInfo("无效推荐人:"+pageParams.employeeCardNo);
                    }
                    gotoLogin();
                });
            }else{
                app.dialog.alert("您还没有登录，等先进行登录!", function () {
                    gotoLogin();
                });
            }
    }
});
$$(document).on('page:init reinit', '.page[data-name="buyCard-page"]', function (e) {
    loadData(userInfo, "buy-userinfo", "show-buy-userinfo");
});
$$(document).on('page:init reinit', '.page[data-name="question-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {method: InterFace.QUESTION_LIST}, "question-list", "show-question-list");
});
$$(document).on('page:init  reinit', '.page[data-name="message-page"]', function (e) {
    if (sessionStorage.hasOwnProperty(TOKEN)) {
        loadBizContent(INTERFACE_URL, {
            method: InterFace.MESSAGE_LIST,
            token: sessionStorage.getItem(TOKEN)
        }, "message-list", "show-message-list");
    }
});
$$(document).on('page:init  reinit', '.page[data-name="account-info-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.ACCOUNT_INFO,
        token: sessionStorage.getItem(TOKEN)
    }, "account-info", "show-account-info");
});
$$(document).on('page:init reinit', '.page[data-name="jiankangka-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.ME_HEART_CARD_INFO,
        token: sessionStorage.getItem(TOKEN)
    }, "jiankangka-info", "show-jiankangka-info");
});
$$(document).on('page:init', '.page[data-name="insurance-product-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {method: InterFace.INSURANCE_LIST}, "insurance-product-list", "show-insurance-product-list");
});
$$(document).on('page:init reinit', '.page[data-name="heart-card-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.USER_HEALTH_CARD_LIST,
        token: sessionStorage.getItem(TOKEN)
    }, "heart-card-list", "show-heart-card-list");
});
$$(document).on('page:init reinit', '.page[data-name="lishi-level-user-view"]', function (e) {
    viewMarketingInformation(sessionStorage.getItem(TOKEN));
});
$$(document).on('page:init reinit', '.page[data-name="changrenlishi-level-user-view"]', function (e) {
    viewMarketingInformationByLishi(sessionStorage.getItem(TOKEN));
});
$$(document).on('page:init reinit', '.page[data-name="wode-baoxian-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.USER_INSURANCE_LIST,
        token: sessionStorage.getItem(TOKEN)
    }, "wode-baoxian-list", "show-wode-baoxian-list");
});
$$(document).on('page:init reinit', '.page[data-name="wode-baoxian-detail-page"]', function (e) {
    let bizContent = {insuranceOrderId: e.detail.route.query.insuranceOrderId};
    loadBizContent(INTERFACE_URL, {
        method: InterFace.USER_INSURANCE_DETAIL,
        token: sessionStorage.getItem(TOKEN),
        bizContent: JSON.stringify(bizContent)
    }, "wode-baoxian-detail", "show-wode-baoxian-detail");
});
$$(document).on('page:init reinit', '.page[data-name="bankcard-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {method: InterFace.BANK_LIST}, "bank-list", "show-bank-list");
});
$$(document).on('page:init reinit', '.page[data-name="zhuanzhang-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.USER_WITHDRAW_RECORD_LIST,
        token: sessionStorage.getItem(TOKEN)
    }, "zhuanzhang-list", "show-zhuanzhang-list");
});
$$(document).on('page:init reinit', '.page[data-name="butiemingxi-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.USER_SUBSIDY_LIST,
        token: sessionStorage.getItem(TOKEN)
    }, "butiemingxi-list", "show-butiemingxi-list");
});
$$(document).on('page:init reinit', '.page[data-name="zhuanzhang-form-page"]', function (e) {
    loadBizContent(INTERFACE_URL, {
        method: InterFace.ACCOUNT_AND_BANK_CARD,
        token: sessionStorage.getItem(TOKEN)
    }, "zhuanzhang-form", "show-zhuanzhang-form");
});
$$(document).on('page:init reinit', '.page[data-name="show-me-page"]', function (e) {
    loadBizContent(userInfo, "show-me", "show-me-view");
});

$$(document).on('page:init reinit', '.page[data-name="heartcheck-page"]', function (e) {
    if (sessionStorage.hasOwnProperty(TOKEN)) {
        getLastCheckInfo(sessionStorage.getItem(TOKEN));
    }
});

$$(document).on('page:init reinit', '.page[data-name="employeeCard-page"]', function (e) {
    if (sessionStorage.hasOwnProperty(TOKEN)) {
        getEmployeeCard(sessionStorage.getItem(TOKEN));
    }
});

$$(document).on('page:init reinit', '.page[data-name="tijian-result-page"]', function (e) {
    let pageParams = e.detail.route.query;
    if (pageParams.checkRecordId) {
        showTijianResult(pageParams.isMember,pageParams.checkRecordId);
    }
});
app.views.create('#view-college', {url: '/college/'});
var memberView = app.views.create('#view-member', {url: '/'});
var heartCheckView = app.views.create('#view-heart_check', {url: '/heart_check/'});


