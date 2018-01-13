/**
 * Created by yexianchao on 2017/12/1.
 */
$.extend({
    //jstree 数据格式 [{"parent":"#","id":"1","text":"aa"},{"parent":"1","id":"4","text":"1"}]
    bindJsTree:function(treeName, url, checkbox, loadedfunction){
        //treeName 元素id, url 数据来源url, checkbox 是否选中, loadedfunction 加载完成后回调函数
        var control = $('#' + treeName)
        control.data('jstree', false);//清空数据，必须

        var isCheck = arguments[2] || false; //设置checkbox默认值为false
        if(isCheck) {
            //复选框树的初始化
            $.getJSON(url, function (result) {
                var datas = result.data;
                control.jstree({
                    'plugins' : [ "checkbox" ], //出现选择框
                    'checkbox': { cascade: "", three_state: false }, //不级联
                    'core': {
                        "multiple": false,
                        'data':datas ,
                        "themes": {
                            "responsive": false
                        }
                    }
                }).bind('loaded.jstree', loadedfunction);
            });
        }else {
            //普通树列表的初始化
            $.getJSON(url, function (data) {
                control.jstree({
                    'core': {
                        'data': data,
                        "themes": {
                            "responsive": false
                        }
                    }
                }).bind('loaded.jstree', loadedfunction);
            });
        }
    },
    clearForm:function (formID) {
        $(':input','#'+formID)
            .not(':button, :submit, :reset, :hidden')
            .val('')
            .removeAttr('checked')
            .removeAttr('selected');
    },
    ////初始化字典下拉列表
    initSeleteDirectory:function (directoryUrl,directoryType,codeValue) {
        //directoryUrl 字典来源url,directoryType 字典类型,codeValue 当value = codeValue时选中
        $.getJSON(directoryUrl, {"dictType":directoryType},function (result) {
            $.each(result.list, function (index, value) {
                if (value.dictCode==codeValue){
                    $("#status").append("<option  selected='selected' value="+value.dictCode+">" + value.dictName + "</option>");
                }else{
                    $("#status").append("<option value="+value.dictCode+">" + value.dictName + "</option>");
                }
            });
        });
    },
    //spu 动态table key-value
    initProperty:function (groupClass,propertyClass,tbodyID,delGroupButton) {
        $("."+groupClass).click(function () {
            var groupProperty = $("#"+tbodyID).find("tr").eq(0);
            var data_index = $("#"+tbodyID).find("tr[data-index]:last").attr("data-index");
            $groupProperty = groupProperty.clone();
            $groupProperty.find("input").eq(0).val("");
            if(delGroupButton==true){
                $groupProperty.find("td").eq(2).append('<button type="button" class="btn btn-sm red btn-outline filter-cancel delGroup"><i class="fa fa-times"></i>删除</button>');
            }
            var parent_index = $("#"+tbodyID).find("tr[parent-index]:last").attr("parent-index");
            if(parent_index==undefined){
                $("#"+tbodyID).append($groupProperty);
            }else {
                $("tr[parent-index]:last").after($groupProperty);
            }
            $groupProperty.find("input[type=text]").each(function(){
               $(this).attr("id",UUID.generate());
            });
            $groupProperty.attr("data-index",++data_index);
            $groupProperty.find("."+propertyClass).click(genChild);
            $groupProperty.find(".delGroup").click(function () {
                var childs = $("tr[parent-index="+data_index+"]");
                childs.each(function () {
                    $(this).remove()
                });
                $(this).parent().parent().remove();
            });
        });
        $("."+propertyClass).click(genChild);
        function genChild(){
            var  fatherTr = $(this).parent().parent();
            var fatherTr_index =fatherTr.attr("data-index");
            var childPropertyTemplate = $(".childPropertyTemplate").eq(0);
            var $childPropertyTemplate = childPropertyTemplate.clone();

            $childPropertyTemplate.find("input[type=text]").each(function(){
                $(this).attr("id",UUID.generate());
            });
            $childPropertyTemplate.removeAttr("class");
            $childPropertyTemplate.removeAttr("style");
            $childPropertyTemplate.find(".delProperty").click(function () {
                $(this).parent().parent().remove();
            });
            var parent_index = $("#"+tbodyID).find("tr[parent-index="+fatherTr_index+"]:last").attr("parent-index");
            if(parent_index==undefined){
                $childPropertyTemplate.attr("parent-index",fatherTr_index);
                fatherTr.after($childPropertyTemplate);
            }else {
                $childPropertyTemplate.attr("parent-index",fatherTr_index);
                $("#"+tbodyID).find("tr[parent-index="+fatherTr_index+"]:last").after($childPropertyTemplate);
            }
        }
    },
    propertyJsonSimple:function(tbodyID,inputID){
        var groups = [];
        var proGroup = $("#"+tbodyID).find("tr[data-index]");
        proGroup.each(function () {
            var group={};
            var key = $(this).find(":input").eq(0).val();
            var value = $(this).find(":input").eq(1).val();
            group.k=key;
            group.v=value;
            groups.push(group);
            if (inputID==undefined){
                return JSON.stringify(groups);
            }else {
                $("#"+inputID).val(JSON.stringify(groups));
            }
        });
    },
    propertyJson:function (tbodyID,inputID) {
        var groups = [];
        var proGroup = $("#"+tbodyID).find("tr[data-index]");
        proGroup.each(function () {
            var group={};
            var params=[];
            var dataIndex = $(this).attr("data-index");
            var groupName = $(this).find(":input").eq(0).val();
            group.group = groupName;
            $("#"+tbodyID).find("tr[parent-index="+dataIndex+"]").each(function () {
                var param = {};
                var key = $(this).find(":input").eq(0).val();
                var value = $(this).find(":input").eq(1).val();
                param.k=key;
                param.v=value;
                params.push(param);
            })
            group.param = params;
            groups.push(group);
        });
        if (inputID==undefined){
            return JSON.stringify(groups);
        }else {
            $("#"+inputID).val(JSON.stringify(groups));
        }
    },
    commonFileInput:function (imgInputID,logoImgResource) {
        var config = {
            showRemove: true,
            showUpload: true,
            showZoom: true,
            showDrag: true,
            removeIcon: '<i class="glyphicon glyphicon-trash text-danger"></i>',
            removeClass: 'btn btn-xs btn-default',
            removeTitle: '移除',
            uploadIcon: '<i class="glyphicon glyphicon-upload text-info"></i>',
            uploadClass: 'btn btn-xs btn-default',
            uploadTitle: 'Upload file',
            zoomIcon: '<i class="glyphicon glyphicon-zoom-in"></i>',
            zoomClass: 'btn btn-xs btn-default',
            zoomTitle: '放大',
            dragIcon: '<i class="glyphicon glyphicon-menu-hamburger"></i>',
            dragClass: 'text-info',
            dragTitle: 'Move / Rearrange',
            dragSettings: {},
            indicatorNew: '<i class="glyphicon glyphicon-hand-down text-warning"></i>',
                indicatorSuccess: '<i class="glyphicon glyphicon-ok-sign text-success"></i>',
            indicatorError: '<i class="glyphicon glyphicon-exclamation-sign text-danger"></i>',
            indicatorLoading: '<i class="glyphicon glyphicon-hand-up text-muted"></i>',
            indicatorNewTitle: 'Not uploaded yet',
            indicatorSuccessTitle: 'Uploaded',
            indicatorErrorTitle: 'Upload Error',
            indicatorLoadingTitle: 'Uploading ...'
        }
        if (logoImgResource!=undefined){
            var singleImg = logoImgResource;
            logoImgResource = [];
            logoImgResource.push('<img src="'+singleImg+'" class="kv-preview-data file-preview-image">');
        }else {
            logoImgResource = [];
        }
        $("#"+imgInputID).fileinput({
            language: 'zh',
            showUpload: false, //是否显示上传按钮
            required: true,
            removeTitle: '删除文件',
            removeLabel: '全部删除',
            browseLabel: '选择文件',
            msgPlaceholder: '请选择文件',
            dropZoneTitle: '请将文件拖拽此区域...',
            msgZoomModalHeading: '文件预览',
            msgNoFilesSelected: '没有选择文件',
            dropZoneEnabled: false,
            msgSelected:'{n}个文件被选中',
            fileActionSettings:config,
            layoutTemplates:{
                actionDelete:'',
                actionUpload:''
            },
            initialPreview: logoImgResource,
            uploadUrl: '#', // you must set a valid URL here else you will get an error
            allowedFileExtensions: ['jpg', 'png', 'gif'],
            overwriteInitial: true,
            maxFileSize: 1000,
            maxFilesNum: 1,
        //allowedFileTypes: ['image', 'video', 'flash'],
            slugCallback: function (filename) {
                return filename.replace('(', '_').replace(']', '_');
            }
        })
    },
    reloadFileInput:function (imgInputID,imgUrl) {
        var template = '<div class="file-loading" id="initDiv"><input id="'+imgInputID+'" class="file" name="logoImg" type="file"data-min-file-count="1" validate="{required: true}"> </div>';
        $("#logoDiv").html(template);
        this.commonFileInput(imgInputID,imgUrl);
    },
    BindSelect:function (ctrlName,url,data,placeholder) {
        if(placeholder==undefined){
            placeholder = '请选择';
        }
        var control = $('#' + ctrlName);
        //设置Select2的处理
        control.select2({
            placeholder:placeholder,
            allowClear: true,
            escapeMarkup: function (m) {
                return m;
            }
        });
        //绑定Ajax的内容
        $.getJSON(url,data,function (data) {
            control.empty();//清空下拉框
            control.append("<option value=''>&nbsp;请选择</option>");
            $.each(data.list, function (i, item) {
                control.append("<option value='" + item.dictCode + "'>&nbsp;" + item.dictName + "</option>");
            });
        });
    },
    initDirectoryList:function (domID,url,data) {
        $.ajax({
            url: url,
            type: "get",
            dataType: "json",
            data:data,
            success: function (data) {
                $.each(data.list, function (i) {
                    $('#'+domID).append("<option value=" + data.list[i].dictCode + ">" + data.list[i].dictName + "</option>");
                });
                $('#'+domID).selectpicker('refresh');
            },
            error: function (data) {
                alert("查询列表失败" + data);
            }
        })
    }
});
//在数组总移除指定值得元素
Array.prototype.removeByValue = function(val) {
    for(var i=0; i<this.length; i++) {
        if(this[i] == val) {
            this.splice(i, 1);
            break;
        }
    }
}


