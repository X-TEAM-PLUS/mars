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
        '<link rel="stylesheet" href="/assets/plugins/framework7-2.0.6/css/framework7.material.css">' +
        '<link rel="stylesheet" href="/assets/plugins/framework7-2.0.6/css/framework7.material.colors.css">' +
        '<link rel="stylesheet" href="/assets/plugins/framework7-2.0.6/css/kitchen-sink.css">'+
        '<link rel="stylesheet" href="/css/my-app.css">'
    );
}
else {
    $$('head').append(
        '<link rel="stylesheet" href="/assets/plugins/framework7-2.0.6/css/framework7.ios.min.css">' +
        '<link rel="stylesheet" href="/assets/plugins/framework7-2.0.6/css/framework7.ios.colors.min.css">' +
        '<link rel="stylesheet" href="/assets/plugins/framework7-2.0.6/css/kitchen-sink.css">'+
        '<link rel="stylesheet" href="/css/my-app.css">'
    );
}
// Init App
var app = new Framework7({
    id: 'io.framework7.testapp',
    root: '#app',
  //  theme: theme,
    routes: routes,
    vi: {
        placementId: 'pltd4o7ibb9rc653x14',
    },
});
