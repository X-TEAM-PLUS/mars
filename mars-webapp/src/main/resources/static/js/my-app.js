var theme = 'ios';
if (location.href.indexOf('theme=md') >= 0) theme = 'md';
var plugin = {
    params: {
        theme: theme,
        root: '#app',
    }
};
if (Framework7.use) Framework7.use(plugin);
else if (Framework7.Class && Framework7.Class.use) Framework7.Class.use(plugin);
var $$ = Dom7;
$$('.view').removeClass('color-theme-pink color-theme-blue color-theme-red color-theme-black color-theme-gray color-theme-orange color-theme-yellow color-theme-green color-theme-white');
$$('.view').addClass('color-theme-red');
$$('.view').removeClass('layout-dark layout-white');
$$('.view').addClass("layout-white");

// Init App
var app = new Framework7({
    id: 'member.center.mars.com',
    root: '#app'
    ,routes: routes
});