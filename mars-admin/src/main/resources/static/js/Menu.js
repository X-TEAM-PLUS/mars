/**
 * Created by yankun on 2016/4/12.
 */
$(function () {
    loadMenu();
});


/**
 * 添加菜单
 * @param menuBox
 * @param value
 */
function addMenu(index,menuBox, value) {
    if (value.children && value.children.length > 0) {
        var menu = $("<li id='nav-item"+index+"' onclick='chooseMenu("+index+");'><a href='"+ (value.url ?value.url: "javascript:;") + "' class='nav-link nav-toggle'><i class='"+value.icon +" font-lg bold uppercase'></i><span class='title'>"+ value.text +"</span><span id='spanSelected"+index+"'></span><span id='spanArrow"+index+"' class='arrow '></span></a></li>");
        menu.append(getChildMenu(value));
    }else{
        var menu = $("<li class='nav-item  ' onclick='chooseMenu("+index+");' ><a href='"+  (value.url ?value.url: "javascript:;")  +"' class='nav-link'><i class='"+value.icon +" font-lg bold uppercase'></i><span class='title'>"+ value.text +"</span></a></li>");
        menu.append(getChildMenu(value));
    }
    menuBox.append(menu);
}

function  chooseMenu(index) {
    $("#spanSelected"+index).addClass(" selected ");
}

/**
 * 获取子菜单
 * @param menuInfo
 */
function getChildMenu(menuInfo) {
    if (menuInfo.children && menuInfo.children.length > 0) {
        var childMenuUL = $("<ul class='sub-menu'></ul>");
        var childItemList = menuInfo.children;
        for (var i = 0; i < childItemList.length; i++) {
            var item = childItemList[i];
            if (item.children && item.children.length > 0) {
                var childMenuLI = $("<li><a href='" + (item.url ?item.url: "javascript:;") + "'><i class='"+item.icon +" font-lg bold uppercase'></i><span>&nbsp;&nbsp;" + item.text + "</span> <span class='arrow'></span></a></li>");
                childMenuLI.append(getChildMenu(item));
            }else{
                var childMenuLI = $("<li><a href='" + (item.url ?item.url: "javascript:;") + "'><i class='"+item.icon +" font-lg bold uppercase'></i><span>&nbsp;&nbsp;" + item.text + "</span></a></li>");
                childMenuLI.append(getChildMenu(item));
            }
            childMenuUL.append(childMenuLI);
        }

        return childMenuUL;
    }
    return null;
}
/**
 * 加载菜单
 */
function loadMenu() {
    var menuBox = $('.page-sidebar-menu');
    $.ajax({
        url: contextPath + "/umplatform/resource/getTree",
        global: false,
        type: "GET",
        dataType: "json",
        async: false,
        success: function (result) {
            $.each(result, function (index, value) {
                addMenu(index,menuBox, value);
            });
        }
    });
}

// function clickMenu(url) {
//
//     iframeLoad(url);
// }
//
// function divLoad(url) {
//     $("#mainWindow").load(url,function(){ $("#mainWindow").fadeIn(100);});
// }
//
//
// function iframeLoad(url) {
//     $("#mainWindow").src = url;
// }