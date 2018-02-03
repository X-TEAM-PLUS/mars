/**
 * 初始化页面
 */
var app = new Framework7({
    id: 'www.mars.com'
    , root: '#app'
    , routes: routes
    , dynamicNavbar: true
});
// Using live 'page:init' event handlers for each page
$$(document).on('page:init', '.page[data-name="home"]', function (e) {
    //获取用户信
    loadUserView('2000000');
});

// Using live 'page:init' event handlers for each page
$$(document).on('page:reinit', '.page[data-name="home"]', function (e) {
    //获取用户信
    loadUserView('2000000');
});

$$(document).on('page:init', '.page[data-name="kaitong"]', function (e) {
    loadData(userInfo, "kaitong-userinfo", "show-kaitong-userinfo");
});
$$(document).on('page:init', '.page[data-name="question-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.QUESTION_LIST}, "question-list", "show-question-list");
});
$$(document).on('page:init', '.page[data-name="message-page"]', function (e) {
    loadBizContent(INTERFACE_URL,{method:InterFace.MESSAGE_LIST,userId:userInfo.userId}, "message-list", "show-message-list");
});
app.views.create('#view-heart_check', {url: '/heart_check/'});
app.views.create('#view-college', {url: '/college/'});
app.views.create('#view-member', {url: '/member_gy/'});

