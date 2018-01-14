var isAndroid = Framework7.prototype.device.android === true;
var isIos = Framework7.prototype.device.ios === true;
Template7.global = {
    android: true
    //android: isAndroid,
    //ios: isIos
};
// If we need to use custom DOM library, let's save it to $$ variable:
var $$ = Dom7;

if (isAndroid) {
    $$('head').append(
        '<link rel="stylesheet" href="path/to/framework7.material.min.css">' +
        '<link rel="stylesheet" href="path/to/framework7.material.colors.min.css">' +
        '<link rel="stylesheet" href="path/to/my-app.material.css">'
    );
}
else {
    $$('head').append(
        '<link rel="stylesheet" href="path/to/framework7.ios.min.css">' +
        '<link rel="stylesheet" href="path/to/framework7.ios.colors.min.css">' +
        '<link rel="stylesheet" href="path/to/my-app.ios.css">'
    );
}

if (isAndroid) {
    // Change class
    $$('.view.navbar-through').removeClass('navbar-through').addClass('navbar-fixed');
    // And move Navbar into Page
    $$('.view .navbar').prependTo('.view .page');
}