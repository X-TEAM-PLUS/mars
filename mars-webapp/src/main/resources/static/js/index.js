// Initialize app
var myApp = new Framework7();



// Add view
var mainView = myApp.views.create('.view-main', {
    // Because we want to use dynamic navbar, we need to enable it for this view:
    dynamicNavbar: true
});