/**
 * Created by yankun on 2016/3/4 0004.
 *
 *
 {
     dataUrl:'',
     dockedItems:[{
         name:'修改'
         ,iconClass:'fa fa-edit'
         ,action:'/xxxx/xxx/edit'
         ,confirm:true
         ,parmaName:'id'
         ,columnName:'id'
     }
     ,{
         name:'删除'
         ,iconClass:'fa fa-edit'
         ,action:'/xxxx/xxx/delete'
         ,confirm:true
         ,parmaName:'id'
         ,columnName:'id'
     }
     ]
     ,pagingtoolbar:{
        pageSize:20
        ,displayInfo: true,
    }
 }
 *
 *
 *
 */
(function ($) {

    /**
     * 初始默认叁数
     * @type {{pageSize: number, pageNo: number}}
     */
    var defaultParams = {
        pageSize: 10
        , pageNo: 1
    };

    var isInited = false;

    $.fn.pagingGrid = function pagingGrid(options) {
        var ColumnType = {
            CheckBox: 'checkbox'
            , Action: 'action'
            , Enum: 'enum'
            , Icon: 'icon'
            , Image: 'image'
            , List: 'list'
        };
        var opts = $.extend({}, defaultParams, options);
        var pageSize = opts.pageSize;
        var pageNo = opts.pageNo;
        var start = (pageNo - 1) * pageSize;
        //是否加载静态数据
        var loadStaticData = opts.loadStaticData;
        //静态结果
        var staticData = opts.staticData;
        var selector = $(this).selector;

        var table = $(selector + " table");
        var form = $(selector + " form");

        var wordBreakStyle = "";
        if (options.scroll) {
            wordBreakStyle = "word-break: keep-all";
        }

        //全选
        $(".allCheckBox").click(function () {
            var v = this.checked
            $.each(table.find("input[type=checkbox]"), function (index, item) {
                item.checked = v;
            })
        });

        //初始化表格
        if (loadStaticData){
            initStaticData(staticData);
        }else {
            init();
        }
        //加载滚动条
        if (!isInited) {
            if (options.scroll) {
                $(table).css("word-break", "keep-all");
                //  $(table).parent().css("min-height","100vh");
                $(table).tableHeadFixer({
                    "head": false
                });
                isInited = true;
            }
        }

        function viewPagingToolBar(result) {
            var limit = (pageNo * pageSize) > result.rowCount ? result.rowCount : (pageNo * pageSize);
            //总页数
            var totalPageCount = Math.ceil(result.rowCount / pageSize);
            //获取翻页对象pagination-toolbar
            var paginationUI = $(selector + " .pagination-toolbar");

            if (paginationUI) {
                paginationUI.empty();
                var rowDiv = $("<div class='row'></div>");
                var displayInfoDiv = $("<div class='col-md-4'> 显示  " + (result.rowCount > 0 ? start + 1 : 0) + " 到  " + limit + " 条，共 " + result.rowCount + " 条</div>");
                var paginationtoolbarPanel = $("<div class='col-md-8'></div>");
                var paginationtoolbarDiv = $(" <ul class='pagination pull-right no-margin'></ul>");
                //上一页
                if (pageNo > 1) {
                    paginationtoolbarDiv.append($("<li><a href=\"javascript:goPage('" + selector + "','" + getPagingParams((pageNo - 1)) + "')\"><i class=\"fa fa-arrow-left\"></li></a></li>"));
                } else {
                    paginationtoolbarDiv.append($("<li  class=\"disabled\"><span><i class=\" disabled fa fa-arrow-left\"></i></span></li>"));
                }

                var pageNoStep = 10;
                var startPageNo = pageNo > pageNoStep ? (pageNo % pageNoStep == 0 ? pageNo - pageNoStep + 1 : pageNo - pageNo % pageNoStep + 1) : 1;
                var maxPageNo = startPageNo + pageNoStep > totalPageCount ? totalPageCount : startPageNo + pageNoStep;

                //首页
                if (pageNo > pageNoStep) {
                    paginationtoolbarDiv.append($("<li><a href=\"javascript:goPage('" + selector + "','" + getPagingParams(1) + "')\">1</a></li>"));
                    paginationtoolbarDiv.append($("<li><span>&hellip;</span></li>"));
                }

                //中间页码
                for (var i = startPageNo; i <= maxPageNo; i++) {
                    var classContent;
                    if (i == pageNo) {
                        classContent = "class=\"active\"";
                    } else {
                        classContent = "";
                    }
                    paginationtoolbarDiv.append($("<li " + classContent + "><a href=\"javascript:goPage('" + selector + "','" + getPagingParams(i) + "')\">" + i + "</a></li>"));
                }

                if (totalPageCount - maxPageNo > 1) {
                    paginationtoolbarDiv.append($("<li><span>&hellip;</span></li>"));
                }

                //尾页
                if (maxPageNo < totalPageCount) {
                    paginationtoolbarDiv.append($("<li><a href=\"javascript:goPage('" + selector + "','" + getPagingParams(totalPageCount) + "')\">" + totalPageCount + "</a></li>"));
                }
                //下一页
                if (totalPageCount <= pageNo) {
                    paginationtoolbarDiv.append($("<li class=\"disabled\"><a href=\"#\"><i class=\"fa  fa-arrow-right\"></li></a></li>"));
                } else {
                    paginationtoolbarDiv.append($("<li><a href=\"javascript:goPage('" + selector + "','" + getPagingParams((pageNo + 1)) + "')\"><i class=\"fa  fa-arrow-right\"></li></a></li>"));
                }

                paginationtoolbarPanel.append(paginationtoolbarDiv);
                rowDiv.append(displayInfoDiv);
                rowDiv.append(paginationtoolbarPanel);
                paginationUI.append(rowDiv);
            }
        }

        /**
         * 获取翻页参数
         * @param pageN
         */
        function getPagingParams(pageN) {
            opts.pageNo = pageN;
            return escape(JSON.stringify(opts));
        }

        /**
         * 实始化表格
         */
        function init() {
            //加载数据
            //$.bootstrapLoading.start({ loadingTips: "正在加载数据，请稍候..." });
            var action = ( opts.dataUrl.indexOf("?") != -1 ? (opts.dataUrl + "&") : (opts.dataUrl + "?")) + "start=" + start + "&limit=" + pageSize + "&dt=" + new Date().getTime();
            //加载数据
            $.ajax({
                url: contextPath + action,
                type: 'Post',
                data: form.serialize(),
                success: function (result) {
                    if (result.success) {
                        $(selector + " tbody").html("");
                        //表格信息
                        $.each(result.list, function (index, rowValue) {
                            var tr = $("<tr style='" + wordBreakStyle + "'></tr>");
                            var tbHead = table.children('thead'); //获取table对象下的thead
                            var tbHeadTh = tbHead.find('tr th'); //获取thead下的tr下的th
                            tbHeadTh.each(function () {//遍历thead的tr下的th
                                var td = getTD($(this), rowValue);
                                tr.append(td);
                            });
                            table.append(tr);

                        });

                        if (opts.pagingtoolbar != 'undefined') {
                            //显示翻页
                            viewPagingToolBar(result);
                        }
                        // setTimeout(function(){
                        //     $.bootstrapLoading.end();
                        // },200);
                        if (options.scroll) {
                            $(table).parent().css("min-height", $(table).height());
                        }
                    }
                }
                , error: function (textStatus, errorThrown) {
                    //$.bootstrapLoading.end();
                    window.wxc.xcConfirm("服务端响应异常:[statusCode:" + textStatus.status + "]", window.wxc.xcConfirm.typeEnum.error);
                }

            });
        }
        //加载静态数据
        function initStaticData(result) {
            //加载数据
            $(selector + " tbody").html("");
            //表格信息
            $.each(result.list, function (index, rowValue) {
                var tr = $("<tr style='" + wordBreakStyle + "'></tr>");
                var tbHead = table.children('thead'); //获取table对象下的thead
                var tbHeadTh = tbHead.find('tr th'); //获取thead下的tr下的th
                tbHeadTh.each(function () {//遍历thead的tr下的th
                    var td = getTD($(this), rowValue);
                    tr.append(td);
                });
                table.append(tr);

            });

            if (opts.pagingtoolbar != 'undefined') {
                //显示翻页
                viewPagingToolBar(result);
            }
            // setTimeout(function(){
            //     $.bootstrapLoading.end();
            // },200);
            if (options.scroll) {
                $(table).parent().css("min-height", $(table).height());
            }
        }

        /**
         * 获取单元格
         * @param tdValue
         * @param rowValue
         */
        function getTD(tdValue, rowValue) {
            if (tdValue.attr("type")) {
                if (tdValue.attr("type").toLowerCase() == ColumnType.CheckBox) {//带复选框的单元格
                    return getCheckBoxTD(tdValue, rowValue);
                } else if (tdValue.attr("type").toLowerCase() == ColumnType.Action) {//带action的单元格
                    return getActionTD(rowValue);
                } else if (tdValue.attr("type").toLowerCase() == ColumnType.Enum) {//枚举类型的单元格
                    return getEnumTD(tdValue, rowValue);
                } else if (tdValue.attr("type").toLowerCase() == ColumnType.Icon) {//枚举类型的单元格
                    return getIconTD(tdValue, rowValue);
                } else if (tdValue.attr("type").toLowerCase() == ColumnType.Image) {//枚举类型的单元格
                    return getImageTD(tdValue, rowValue);
                } else if (tdValue.attr("type").toLowerCase() == ColumnType.List) {
                    return getList(tdValue, rowValue);
                }
            } else {
                //普通单元格
                return getSimpleTD(tdValue, rowValue);
            }
        }

        function getList(tdValue, rowValue) {
            var style = ["label-success", "label-info", "label-warning", "label-danger"];
            var valueName = tdValue.attr("column");
            var value = getColumnValue(rowValue, valueName);
            var td = "<td>";
            var list = JSON.parse(value);
            for (var i = 0; i < list.length; i++) {
                var br = "</br>";
                if (i == list.length - 1)
                    br = "";
                var index = i % style.length;
                for (var key in list[i]) {
                    td += "<span class=\"label label-sm " + style[index] + "\">[" + key + ":" + list[i][key] + "]</span>" + br;
                }
            }
            td += "</td>";
            return $(td);
        }

        /**
         * 获取icon类型单元格
         * @param tdValue
         * @param rowValue
         */
        function getIconTD(tdValue, rowValue) {
            var valueName = tdValue.attr("column");
            return $("<td  style='" + wordBreakStyle + "'><span class=' " + rowValue[valueName] + "'></span></span></td>");
        }

        /**
         * 获取Image类型单元格
         * @param tdValue
         * @param rowValue
         */
        function getImageTD(tdValue, rowValue) {
            var valueName = tdValue.attr("column");
            var imageWidth="40px";
            var imageHeight="40px";
            if(tdValue.attr("imageWidth")){
                imageWidth=tdValue.attr("imageWidth");
            }
            if(tdValue.attr("imageHeight")){
                imageHeight=tdValue.attr("imageHeight");
            }


            return $("<td  style='" + wordBreakStyle + "'><img src='http://img.test.bestlease.com/" + getColumnValue(rowValue, valueName) + "' style='width: "+ imageWidth+";height: "+ imageHeight+"'></span></td>");
        }

        /**
         * 获取枚举类型单元格
         * @param tdValue
         * @param rowValue
         */
        function getEnumTD(tdValue, rowValue) {
            var valueName = tdValue.attr("column");
            var convertValue = eval("(" + tdValue.attr("enum-v") + ")");
            if(rowValue[valueName] != 0 && !rowValue[valueName]){
                return $("<td  style='" + wordBreakStyle + "'></td>");
            }else if (convertValue[rowValue[valueName]] != null) {
                return $("<td  style='" + wordBreakStyle + "'>" + convertValue[rowValue[valueName]] + "</td>");
            } else {
                return $("<td  style='" + wordBreakStyle + "'>" + rowValue[valueName] + "</td>");
            }
        }

        /**
         * 普通单元格
         * @param dock
         * @param rowValue
         * @returns {*|HTMLElement}
         */
        function getSimpleTD(tdValue, rowValue) {
            var valueName = tdValue.attr("column");
            if (valueName) {
                return $("<td style='" + wordBreakStyle + "'>" + getColumnValue(rowValue, valueName) + "</td>");
            }
            return $("<td ></td>");
        }


        /**
         * 获取button DOM
         * @param dock
         * @param rowValue
         * @returns {*|HTMLElement}
         */
        function getButtonDom(dock, rowValue) {
            var url = contextPath + dock.action;
            if (dock.parmaName != undefined) {
                var columnValue = getColumnValue(rowValue, dock.column);
                if (dock.columnType != undefined) {
                    columnValue = columnValue.substr(0, 10);
                }
                if (url.indexOf("?") == -1) {
                    url += "?" + dock.parmaName + "=" + columnValue;
                } else {
                    url += "&" + dock.parmaName + "=" + columnValue;
                }
            }

            var parmas = dock.name + "," + url + "," + dock.confirm + "," + dock.ajax;
            var buttonColor = "green";
            if (typeof(dock.buttonColor) != 'undefined') {
                buttonColor = dock.buttonColor;
            }
            var button;
            var title = "";
            if (dock.name != undefined && dock.name != '') {
                title = "title = '" + dock.name + "'";
            }
            if (dock.onclick != null && dock.onclick != 'undefined') {
                button = "<button " + title + " class='btn " + buttonColor + " btn-flat btn-xs'";
                button += " onclick='" + dock.onclick + "(\"" + getColumnValue(rowValue, dock.column) + "\")'";
                if (dock.datatoggle != null && dock.datatoggle != 'undefined') {
                    button += " data-toggle='" + dock.datatoggle + "'";
                }
                if (dock.datatarget != null && dock.datatarget != 'undefined') {
                    button += " data-target='" + dock.datatarget + "'";
                }

                button += "><i class='" + dock.iconClass + "'></i></button>";
            } else {
                button = $("<button " + title + " class='btn " + buttonColor + " btn-flat btn-xs' onclick=\"execAction('" + selector + "','" + escape(parmas) + "','" + getPagingParams(opts.pageNo) + "');\"><i class='" + dock.iconClass + "'></i></button>");
            }

            return button;
        }

        /**
         * 拼装URL
         * @param dock
         * @param rowValue
         * @returns {string}
         */
        function getUrl(dock, rowValue) {
            var url = contextPath + ( dock.action.indexOf("?") != -1 ? (dock.action + "&") : (dock.action + "?"));
            if (Object.prototype.toString.call(dock.parmaName) == '[object Array]') {
                for (var i = 0; i < dock.parmaName.length; i++) {
                    url += dock.parmaName[i] + "=" + getColumnValue(rowValue, dock.column[i]) + "&";
                }
            } else {
                url += dock.parmaName + "=" + getColumnValue(rowValue, dock.column);
            }
            return url;
        }

        /**
         * 获取按钮组
         * @param dock
         * @param rowValue
         */
        function getButtonGroupDom(dock, rowValue) {
            var dom = $("<div class='btn-group'></div>");
            var buttonGroup = $("<button  class='btn green dropdown-toggle btn-sm'  data-toggle='dropdown'><i class='" + dock.iconClass + "'></i> " + dock.name + " <i class='fa fa-angle-down'></i> </button>");
            var dropdownMenu = $("<ul class='dropdown-menu' role='menu'></ul>");
            if (dock.items != 'undefined' && dock.items.length > 0) {
                $.each(dock.items, function (index, item) {
                    var url = getUrl(dock, rowValue);
                    var parmas = item.name + "," + url + "," + item.confirm + "," + item.ajax;
                    var button = $("<li><a  href=\"javascript:execAction('" + selector + "','" + escape(parmas) + "','" + getPagingParams(opts.pageNo) + "');\"><i class='" + item.iconClass + "'></i> " + item.name + " </a></li>");
                    //控制可见性
                    if (typeof(item.equalField) != 'undefined' && typeof(item.equalValue) != 'undefined') {
                        var checkValue = getColumnValue(rowValue, item.equalField);
                        if (checkValue == item.equalValue) {
                            dropdownMenu.append(button);
                        }
                        if (checkValue == "") {
                            dropdownMenu.append(button);
                        }
                    } else if (typeof(item.notEqualField) != 'undefined' && typeof(item.notEqualField) != 'undefined') {
                        var checkValue = getColumnValue(rowValue, item.notEqualField);
                        if (checkValue != item.notEqualValue) {
                            dropdownMenu.append(button);
                        }
                    } else {
                        dropdownMenu.append(button);
                    }

                });
            }
            dom.append(buttonGroup);
            dom.append(dropdownMenu);
            return dom;
        }

        function getXtypeDom(dock, rowValue) {
            var xtype = "button";
            if (typeof(dock.xtype) != 'undefined') {
                xtype = dock.xtype;
            }
            if (xtype == 'button') {
                return getButtonDom(dock, rowValue);
            } else if (xtype == 'buttongroup') {
                return getButtonGroupDom(dock, rowValue);
            }
        }

        /**
         * action按钮
         * @param rowValue
         * @returns {*|HTMLElement}
         */
        function getActionTD(rowValue) {
            var td = $("<td style='" + wordBreakStyle + "'></td>");
            if (opts.dockedItems != 'undefined' && opts.dockedItems.length > 0) {
                //表格信息
                $.each(opts.dockedItems, function (index, dock) {
                    //控制可见性
                    if (typeof(dock.equalField) != 'undefined' && typeof(dock.equalValue) != 'undefined') {
                        var checkValue = getColumnValue(rowValue, dock.equalField);
                        if (checkValue == dock.equalValue) {
                            td.append(getXtypeDom(dock, rowValue));
                        }
                    } else {
                        td.append(getXtypeDom(dock, rowValue));
                    }
                });
            }
            return td;
        }

        /**
         * 带复选框的单元格
         * @param tdValue
         * @param rowValue
         * @returns {*|HTMLElement}
         */
        function getCheckBoxTD(tdValue, rowValue) {
            return $("<td><input type=\"checkbox\" name='" + tdValue.attr("column") + "' value='" + rowValue[tdValue.attr("column")] + "' class=\" icheckbox \"  data-checkbox=\"icheckbox_square-grey\"></td>");
        }


        /**
         * 根据列名获取列值
         * @param rowValue
         * @param columnName
         * @returns {*}
         */
        function getColumnValue(rowValue, columnName) {
            if (columnName) {
                if (columnName.split(".").length > 1) {
                    var rootValue;
                    var valueNames = columnName.split(".");
                    for (var i = 0; i < valueNames.length; i++) {
                        if (rootValue != undefined) {
                            rootValue = rootValue[valueNames[i]];
                        } else {
                            rootValue = rowValue[valueNames[i]];
                        }
                    }

                    if(rootValue == undefined || rootValue == "null"){
                        rootValue = "";
                    }
                    return rootValue;
                } else {
                    return rowValue[columnName] != null ? rowValue[columnName] : "";
                }
            }
            return "";
        }
    }

    /**
     * 获取复选框的值
     * @returns {string}
     */
    $.fn.getTableSelected = function getTableSelected() {
        var checkedIds = "";
        $(this.selector).find('input[type="checkbox"]:checked').each(function () {
            if (this.checked) {
                checkedIds = checkedIds + this.value + ","
            }
        });
        if (checkedIds && checkedIds.length > 1) {
            checkedIds = checkedIds.substr(0, checkedIds.length - 1);
        }
        return checkedIds;
    }

})(jQuery);

/**
 *
 * @param obj
 * @param pageNo
 */
function goPage(selector, opts) {
    var options = eval("(" + unescape(opts) + ")");
    $(selector).pagingGrid(options);

}

/**
 * 执行action
 * @param options
 */
function execAction(selector, parmas, pagingParams) {
    var params = unescape(parmas).split(",");
    var action = {};
    action.name = params[0];
    action.url = params[1];
    action.confirm = params[2];
    action.ajax = params[3];
    if ("undefined" != action.confirm && 'true' == action.confirm) {
        confirmAction(selector, action, pagingParams);
    } else {
        if ("undefined" != action.ajax) {
            ajaxAction(selector, action, pagingParams);
        } else {
            urlAction(selector, action, pagingParams);
        }
    }

    function urlAction(selector, action, pagingParams) {
        location.href = action.url;
        goPage(selector, pagingParams);
    }

    function ajaxAction(selector, action, pagingParams) {
        $.ajax({
            url: action.url + "&dt=" + new Date().getTime(),
            type: 'GET',
            success: function (result) {
                var success = result.success;
                if (success) {
                    window.wxc.xcConfirm(action.name + "成功。", window.wxc.xcConfirm.typeEnum.success);
                    goPage(selector, pagingParams);
                } else {
                    window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.error);
                }
            }
            , error: function (textStatus, errorThrown) {
                window.wxc.xcConfirm("服务端响应异常:[statusCode:" + textStatus.status + "]", window.wxc.xcConfirm.typeEnum.error);
            }
        });
    }

    /**
     * 确认Action
     * @param action
     * @constructor
     */
    function confirmAction(selector, action, pagingParams) {
        window.wxc.xcConfirm('确定要' + action.name + '么?', window.wxc.xcConfirm.typeEnum.confirm, {
            title: '提示'
            , onOk: function () {
                if ("undefined" != action.ajax) {
                    ajaxAction(selector, action, pagingParams);
                } else {
                    urlAction(selector, action, pagingParams);
                }
            }
        });
    }
}


