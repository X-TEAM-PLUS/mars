/**
 * Created by yankun on 2016/4/19.
 */
(function ($) {
    var indenter = 20;
    $.fn.loadTreeTable = function loadData(options) {
        var checkedNode  = new Map();
        var opts = $.extend({}, options);
        var selector = this.selector;
        var tbody = $(selector + " tbody");
        var parmaInput = $("#"+options.parmaName);
        tbody.html("");
        var url = opts.url.indexOf("?")!=-1?(opts.url+"&") :(opts.url+"?");
        $.bootstrapLoading.start({ loadingTips: "正在加载数据，请稍候..." });
        $.ajax({
            url: contextPath + url + "dt=" + new Date().getTime(),
            type: 'Post',
            success: function (result) {
                var level = 0;
                //表格信息
                $.each(result, function (index, value) {
                    addRow(selector, tbody, value, opts,level);
                    if (value.children && value.children.length > 0) {//如果有子节点
                        addChildRow(selector, tbody, value.children, opts,level+1);
                    }
                });
                $(selector).treetable({
                    expandable: options.expandable
                });
                if(options.expandable){
                    $(selector).treetable('expandAll');
                }
                $(selector+" tbody tr").click(function () {  //点击事件，复选框
                    if(options.checkBox){
                        // var checked = $("#checkbox"+ $(this).attr("data-tt-id")).attr("checked");
                        // rowClick(selector,$(this),!checked);
                    }else{
                        $(selector+" tbody tr").each(function () {
                            $(this).removeClass("selected");
                        });
                        $(this).addClass("selected");
                        if(parmaInput) {
                            parmaInput.val($(this).attr("data-tt-id"));
                        }
                    }
                });
                $.bootstrapLoading.end();
            }
            ,error:function (textStatus, errorThrown) {
                $.bootstrapLoading.end();
                window.wxc.xcConfirm("服务端响应异常:[statusCode:"+ textStatus.status +"]", window.wxc.xcConfirm.typeEnum.error);
            }
        });

        /**
         * 设置值
         */
        function setInputValue(checkbox,checked) {
            if(parmaInput && checkbox.attr("value")!=checkbox.attr("parentid")){
                if(checked){
                    if(!checkedNode.containsKey(checkbox.attr("value"))){
                        checkedNode.put(checkbox.attr("value"),checkbox);
                    }
                }else{
                    if(checkedNode.containsKey(checkbox.attr("value"))){
                        checkedNode.remove(checkbox.attr("value"));
                    }
                }
               parmaInput.val(checkedNode.keys().join(","));
            }
        }

        /**
         * 添加行
         * @param selector
         * @param value
         * @returns {*|HTMLElement}
         */
        function addRow(selector, tbody, value, opts,level) {
            var row = $("<tr data-tt-id ='" + value.id + "' data-tt-parent-id='" + (value.id == value.parentId ? "" : value.parentId) + "' data-text='" + value.text +"'data-depth='" + value.depth +"'></tr>");
            if(value['checked']){
                checkedNode.put(row.attr('data-tt-id'),row);
            }
            var tbHead = $(selector).children('thead'); //获取table对象下的thead
            var tbHeadTh = tbHead.find('tr th'); //获取thead下的tr下的th
            var start = true;
            tbHeadTh.each(function () {//遍历thead的tr下的th
                var valueName = $(this).attr("column");

                if (valueName) {
                    if (value[valueName]) {
                        if (opts.checkBox && start) {
                            row.append($("<td>" + getCheckBox(value, level) + "</td>"));
                        }else if(start){
                            row.append($("<td><label style='margin: 0 0 0 "+ (level*indenter)+"px;'><i class='" +value.icon +"    font-lg  font-blue bold uppercase '></i> " + value[valueName] + "</label></td>"));
                        } else {
                            row.append($("<td><label >" + value[valueName] + "</label></td>"));
                        }
                        start = false;
                    } else {
                        row.append($("<td></td>"));
                    }
                }
            });
            tbody.append(row);

            $('input').iCheck({
                labelHover : false,
                cursor : true,
                checkboxClass : 'icheckbox_square-green',
                radioClass : 'iradio_square-green',
                increaseArea : '20%'
            });
            $('.icheckbox').on('ifChecked ', function(event){
                $(this).find("input[type=checkbox]").attr("checked", true);
                setInputValue($(this).find("input[type=checkbox]"),true);
                var selectNodeValue = $(this).find("input[type=checkbox]").attr("value");
                tbody.find("tr").each(function () {
                    if(selectNodeValue==$(this).attr("data-tt-parent-id")){
                        $(this).find(".icheckbox").iCheck('check');
                    }
                });
            });
            $('.icheckbox').on(' ifUnchecked', function(event){
                $(this).find("input[type=checkbox]").attr("checked", false);
                setInputValue($(this).find("input[type=checkbox]"),false);
                var selectNodeValue = $(this).find("input[type=checkbox]").attr("value");
                tbody.find("tr").each(function () {
                    if(selectNodeValue==$(this).attr("data-tt-parent-id")){
                        $(this).find(".icheckbox").iCheck('uncheck');
                    }
                });
            });
        }

        /**
         * 添加子节点
         * @param selector
         * @param children
         */
        function addChildRow(selector, tbody, childrens, opts,level) {
            for (var i = 0; i < childrens.length; i++) {
                var childRow = childrens[i];
                addRow(selector, tbody, childRow, opts,level);
                if (childRow.children && childRow.children.length > 0) {//如果有子节点
                    addChildRow(selector, tbody, childRow.children, opts,level+1);
                }
            }
        }

        /**
         * 获取checkbox
         * @param row
         * @returns {string}
         */
        function getCheckBox(row,level) {
            var checked = row.checked?"checked='checked'":"";
            var checkBoxValue ="<span class='icheckbox' style='margin: 0 0 0 "+ (level*indenter)+"px;'>"
                +"<input  type='checkbox' id='checkbox"+ row.id +"' value='"+ row.id +"' "+ checked +" parentId='"+ row.parentId +"' "+" >"
                    + "<i class='" +row.icon +"  font-lg  font-blue bold uppercase '></i> "+row.text
                +"</span>";

            return checkBoxValue;
        }
    }



    /**
     * 获取复选框的值
     * @returns {string}
     */
    $.fn.getCheckedNodes = function getCheckedNodes() {
        var checkedIds = "";
        $(this.selector).find('input[type="checkbox"]:checked').each(function () {
            if($(this).attr("parentId")!=$(this).attr("value")){
                checkedIds = checkedIds + $(this).attr("value")+","
            }
        });
        if(checkedIds && checkedIds.length>1){
            checkedIds = checkedIds.substr(0,checkedIds.length-1);
        }
        return checkedIds;
    }

    /**
     * 获取单选
     * @returns {Object}
     */
    $.fn.getSingleSelectedNode = function getSingleSelectedNode() {
        var checkNode = new Object();
        if($(this.selector +" .selected").length >0){
            $(this.selector +" .selected").each(function () {
                checkNode.id =  $(this).attr("data-tt-id");
                checkNode.text =   $(this).attr("data-text");
                checkNode.parentId =  $(this).attr("data-tt-parent-id");
                checkNode.depth =  $(this).attr("data-depth");
                return checkNode;
            })
        }
        return checkNode;
    }
})(jQuery);