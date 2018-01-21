<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<#import '/common/common.ftl' as commonMacro>
<@commonMacro.commonStyle />
    <@commonMacro.commonScript />
</head>
<body class="page-content">

<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a href="/welcome">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <i class="fa fa-cube"></i>
            <a href="index">地方理事会信息表管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li class=" active">
            <i class="fa fa-plus "></i>
            <a href="#">添加</a>
        </li>
    </ul>
</div>
<div class="page-content">
    <div class="tab-pane active" id="tab_0">
        <div class="portlet box grey-cascade">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-adn"></i>添加地方理事会信息表
                </div>
                <div class="tools">
                    <a href="javascript:;" class="collapse">
                    </a>
                    <a href="#portlet-config" data-toggle="modal" class="config">
                    </a>
                    <a href="javascript:;" class="reload">
                    </a>
                    <a href="javascript:;" class="remove" data-original-title="" title="" onclick="history.go(-1)">
                    </a>
                </div>
            </div>
            <div class="portlet-body form">
                <!-- BEGIN FORM-->
                <form action="/services/mars/councilinfo/post" class="form-horizontal">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                城市编号<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <select class="area" id="cityNo" name="cityNo"
                                        style="width: 100%">

                                </select>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                添加成员
                            </label>
                            <div class="col-md-5">

                                <select class="userInfo" id="userInfo" name="userInfo" multiple="multiple"
                                        style="width: 100%">
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                选择职务
                            </label>
                            <div class="col-md-5">

                                <select class="jobType" id="jobType" name="jobType"
                                        style="width: 100%">
                                    <option value="NOT_JOB">成员</option>
                                    <option value="DEPUTY_DIRECTOR_GENERAL">副理事长</option>
                                    <option value="ADMINISTARATION">行政</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>

                    <div class="form-actions right">
                        <button type="button" onclick="addUser()" class="btn green"><i class="fa fa-check"></i> 添加
                        </button>
                    </div>
                <form name="form1">
                    <div class="portlet-body" id="userInfoTable">
                        <table class="table table-striped table-bordered table-hover ">
                            <thead>
                            <tr>
                                <th column="userId">用户ID</th>
                                <th column="userInfo.realName">真实姓名</th>
                                <th column="userInfo.mobileNo">手机号</th>
                                <th column="userInfo.lastLoginTime">最后登录时间</th>
                                <th column="userInfo.loginCount">登录次数</th>
                                <th column="localCouncilMember.JOB_TYPE">职务</th>
                                <th type="action" style="width: 200px">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <div class="table-toolbar pagination-toolbar">
                        </div>
                    </div>
                    <div class="form-actions right">
                        <button type="button" class="btn default" onclick="history.go(-1)"><i
                                class="fa fa-rotate-left"></i> 取消
                        </button>
                        <button type="submit" class="btn green"><i class="fa fa-check"></i> 保存</button>
                    </div>
                </form>
                <!-- END FORM-->
            </div>
        </div>
    </div>
</div>

<script>

    var jobType = $(".jobType").select2({minimumResultsForSearch: Infinity});


    var userInfo = $(".userInfo").select2({
        theme: "bootstrap",
        placeholder: "增加成员",
        allowClear: true,
        ajax: {
            url: "/services/mars/userinfo/list?start=0&limit=50",
            dataType: "json",
            delay: 250,
            data: function (params) {
                return {
                    realName: params.term
                };
            },
            cache: true,
            processResults: function (res, params) {
                var options = [];
                for (var i = 0; i < res.list.length; i++) {
                    var option = {"id": res.list[i].userId, "text": res.list[i].realName};
                    options.push(option);
                }
                return {
                    results: options,
                    pagination: {}
                };
            },
            escapeMarkup: function (markup) {
                return markup;
            },

            results: function (data, page) { // parse the results into the format expected by Select2.
                // since we are using custom formatting functions we do not need to alter remote JSON data
                for (var i = 0; i < data.length; i++) {
                    data[i].id = data[i].id + "|" + data[i].name;
                }
                ;
                data.push({id: "577cb125f8d2c404572413d1|无", name: "无"});
                return {
                    results: data
                };
            }
            , minimumInputLength: 1,
            initSelection: function (element, callback) {
                var data = [];
                var value = ""
                var str = $(element).val().split('^');
                for (var i = 0; i < str.length; i++) {
                    var temp = JSON.parse(str[i]);
                    value += temp.id + ",";
                    data.push(temp);
                }
                ;
                value = value.substring(0, value.length - 1);
                $(element).val(value);
                callback(data);

            },
            formatSelection: function (item) {
                return item.name;//注意此处的name，要和ajax返回数组的键值一样
            }, // 选择结果中的显示
            formatResult: function (item) {
                return item.name;//注意此处的name
            },// 搜索列表中的显示
        }
    });

    var area = $(".area").select2({
        theme: "bootstrap",
        allowClear: true,
        placeholder: "选择地市",
        ajax: {
            url: "/services/mars/cityinfo/list?start=0&limit=50&areaType=1",
            dataType: "json",
            delay: 250,
            data: function (params) {
                return {
                    name: params.term
                };
            },
            cache: true,
            processResults: function (res, params) {
                var options = [];
                for (var i = 0; i < res.list.length; i++) {
                    var option = {"id": res.list[i].code, "text": res.list[i].name};
                    options.push(option);
                }
                return {
                    results: options,
                    pagination: {}
                };
            },
            escapeMarkup: function (markup) {
                return markup;
            },
            minimumInputLength: 1
        }
    });

    function init() {
        $.ajax({
            url: "/services/mars/cityinfo/list?start=0&limit=50&areaType=1",
            type: 'GET',
            success: function (result) {
                for (var i = 0; i < result.list.length; i++) {
                    $(".area").append("<option value='" + result.list[i].code + "' selected >" + result.list[i].name + "</option>");
                }
                $.ajax({
                    url: "/services/mars/councilinfo/get?councilId=" + getParameter("councilId"),
                    type: 'GET',
                    success: function (result) {
                        area.val(result.data.cityNo).trigger("change");
                    }
                    , error: function (textStatus, errorThrown) {
                        window.wxc.xcConfirm("服务端响应异常:[statusCode:" + textStatus.status + "]", window.wxc.xcConfirm.typeEnum.error);
                    }

                });
            }
            , error: function (textStatus, errorThrown) {
                window.wxc.xcConfirm("服务端响应异常:[statusCode:" + textStatus.status + "]", window.wxc.xcConfirm.typeEnum.error);
            }

        });

        $("#userInfoTable").pagingGrid(
                {
                    dataUrl: '/services/mars/userrelation/listCouncil?councilId=' + getParameter("councilId")
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [{
                        name: '删除'
                        , iconClass: 'glyphicon glyphicon-trash'
                        , action: '/services/mars/councilinfo/deleteUser?councilId=' + getParameter("councilId")
                        , confirm: true
                        , ajax: true
                        , parmaName: 'userInfo'
                        , column: 'refereeUserId'
                    }],
                    pagingtoolbar: {
                        displayInfo: true
                    }
                }
        );
    }

    init();

    function removeUser(val){
        alert("removeUser :   " + val);
    }

    function addUser() {
        if (jobType.val() == null || jobType.val() == undefined) {
            window.wxc.xcConfirm("没有选择职务,请先选择职务!", window.wxc.xcConfirm.typeEnum.error);
        }
        $.ajax({
            url: "/services/mars/councilinfo/postUser?userInfo=" + userInfo.val() + "&councilId=" + getParameter("councilId")+"&jobType="+jobType.val(),
            type: 'GET',
            success: function (result) {

                if (result.success) {
                    if (result.message) {
                        window.wxc.xcConfirm(result.message ? result.message : "提交成功", window.wxc.xcConfirm.typeEnum.success, {
                            title: '提示'
                            , onOk: function () {
                                location.href = "edit?councilId=" + getParameter("councilId");
                            }
                        });
                    }

                } else {
                    window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.error);
                }
            }
            , error: function (textStatus, errorThrown) {
                window.wxc.xcConfirm("服务端响应异常:[statusCode:" + textStatus.status + "]", window.wxc.xcConfirm.typeEnum.error);
            }

        });
    }
</script>
</body>
</html>


