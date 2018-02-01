// Init/Create views
var viewHeartCheck = app.views.create('#view-heart_check', {
    url: '/heart_check/'
});
var viewGoodDoctor = app.views.create('#view-good_doctor', {
    url: '/good_doctor/'
});
var viewMember = app.views.create('#view-member', {
    url: '/member_gy/'
});

//获取用户信
loadBizContent(INTEFACE_URL
    ,{ method:'com.mars.gateway.user.getUserInfo',userId:'2000000' }
    ,'userInfo'
    ,'show-useriInfo'
);