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
$$(document).on('page:init', '.page[data-name="kaitong"]', function (e) {
    loadData(userInfo, "kaitong-userinfo", "show-kaitong-userinfo");
});
$$(document).on('page:init', '.page[data-name="question-page"]', function (e) {
    loadData(INTERFACE_URL,{limit:9999999}, "question-list", "show-question-list");
});

var viewHeartCheck = app.views.create('#view-heart_check', {
    url: '/heart_check/'
});
var viewGoodDoctor = app.views.create('#view-good_doctor', {
    url: '/good_doctor/'
});
var viewMember = app.views.create('#view-member', {
    url: '/member_gy/'
});

