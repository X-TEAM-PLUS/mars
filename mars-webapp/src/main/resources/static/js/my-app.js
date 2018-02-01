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

// $$('.view').removeClass('color-theme-pink color-theme-blue color-theme-red color-theme-black color-theme-gray color-theme-orange color-theme-yellow color-theme-green color-theme-white');
// $$('.view').addClass('color-theme-red');
// $$('.view').removeClass('layout-dark layout-white');
// $$('.view').addClass("layout-white");

// Init App
var app = new Framework7({
    id: 'www.mars.com'
    ,root: '#app'
    ,routes: routes
    ,dynamicNavbar: true
});

/**
 * 获取 bizContent
 * @param url  请求地址
 * @param domId
 * @param templateId
 */
var INTEFACE_URL="http://localhost:8000/api/gateway";
function loadBizContent(url,params,domId,templateId) {
// Template
    var template = document.getElementById(templateId).innerHTML;
    var compiled = Template7(template).compile();
//获取用户信
    app.request.json(url, params, function (data) {
        var bizContent = JSON.parse(data.bizContent);
        var compiledRendered = compiled(bizContent);
        document.getElementById(domId).innerHTML = compiledRendered;
    });
}