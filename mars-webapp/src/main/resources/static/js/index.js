/**
 * 初始化页面
 */
var app = new Framework7({
    id: 'www.mars.com'
    , root: '#app'
    , routes: routes
    , dynamicNavbar: true
});
$$(document).on('page:init', '.page[data-name="home"]', function (e) {
    //获取用户信
    loadUserView(userInfo.userId);
});

$$(document).on('page:reinit', '.page[data-name="home"]', function (e) {
    //获取用户信
    loadUserView(userInfo.userId);
});
$$(document).on('page:init', '.page[data-name="buyCard-page"]', function (e) {
    loadData(userInfo, "buy-userinfo", "show-buy-userinfo");
});
$$(document).on('page:init', '.page[data-name="question-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.QUESTION_LIST}, "question-list", "show-question-list");
});
$$(document).on('page:init', '.page[data-name="message-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.MESSAGE_LIST,userId:userInfo.userId}, "message-list", "show-message-list");
});

$$(document).on('page:init', '.page[data-name="account-info-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.ACCOUNT_INFO,userId:userInfo.userId}, "account-info", "show-account-info");
});

$$(document).on('page:init', '.page[data-name="jiankangka-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.ME_HEART_CARD_INFO,userId:userInfo.userId}, "jiankangka-info", "show-jiankangka-info");
});

$$(document).on('page:init', '.page[data-name="insurance-product-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.INSURANCE_LIST}, "insurance-product-list", "show-insurance-product-list");
});

$$(document).on('page:init', '.page[data-name="heart-card-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.USER_HEALTH_CARD_LIST,userId:userInfo.userId}, "heart-card-list", "show-heart-card-list");
});
$$(document).on('page:init', '.page[data-name="wode-baoxian-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.USER_INSURANCE_LIST,userId:userInfo.userId}, "wode-baoxian-list", "show-wode-baoxian-list");
});

$$(document).on('page:init', '.page[data-name="wode-baoxian-detail-page"]', function (e) {
    var bizContent = {insuranceOrderId:e.detail.route.query.insuranceOrderId};
    loadBizContent(INTERFACE_URL,{method:InterFace.USER_INSURANCE_DETAIL,userId:userInfo.userId,bizContent:JSON.stringify(bizContent)}, "wode-baoxian-detail", "show-wode-baoxian-detail");
});
$$(document).on('page:init', '.page[data-name="bankcard-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.BANK_LIST}, "bank-list", "show-bank-list");
});

$$(document).on('page:init', '.page[data-name="zhuanzhang-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.USER_WITHDRAW_RECORD_LIST,userId:userInfo.userId}, "zhuanzhang-list", "show-zhuanzhang-list");
});

$$(document).on('page:init', '.page[data-name="butiemingxi-list-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.USER_SUBSIDY_LIST,userId:userInfo.userId}, "butiemingxi-list", "show-butiemingxi-list");
});
app.views.create('#view-heart_check', {url: '/heart_check/'});
app.views.create('#view-college', {url: '/college/'});
var memberView = app.views.create('#view-member', {url: '/'});




