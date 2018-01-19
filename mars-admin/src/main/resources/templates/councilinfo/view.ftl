<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<#import '/common/common.ftl' as commonMacro>
<@commonMacro.commonStyle />
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
            <i class="fa fa-edit "></i>
            <a href="#">编辑</a>
        </li>
    </ul>
</div>
<div class="page-content">
    <div class="tab-pane active" id="tab_0">
        <div class="portlet box grey-cascade">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-adn"></i>编辑地方理事会信息表
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
                <form action="/services/mars/councilinfo/put" class="form-horizontal" id="councilinfoForm">
                    <input type="hidden" name="councilId" id="councilId">

                    <div class="form-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        城市主键
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="cityNo" class="form-control"
                                               id="cityNo"
                                               placeholder="用户主键" style="width: 100%" readonly="true">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        城市名称
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="cityName" class="form-control"
                                               id="cityName"
                                               placeholder="用户昵称" style="width: 100%"
                                               validate='{"required": true,"number":true}' readonly="true"
                                               maxlength="3">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        开通时间
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="openDate" class="form-control"
                                               id="openDate"
                                               placeholder="用户主键" style="width: 100%" readonly="true">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        理事会成员人数
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="totalUser" class="form-control"
                                               id="totalUser"
                                               placeholder="用户昵称" style="width: 100%"
                                               validate='{"required": true,"number":true}' readonly="true"
                                               maxlength="3">
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        总补贴
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="totalSubsidy" class="form-control"
                                               id="totalSubsidy"
                                               placeholder="用户主键" style="width: 100%" readonly="true">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="portlet-body" id="userInfoTable">

                        <table class="table table-striped table-bordered table-hover ">
                            <thead>
                            <tr>
                                <th column="userId">用户ID</th>
                                <th column="userInfo.realName">真实姓名</th>
                                <th column="userInfo.mobileNo">手机号</th>
                                <th column="userInfo.lastLoginTime">最后登录时间</th>
                                <th column="userInfo.loginCount">登录次数</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <div class="table-toolbar pagination-toolbar">
                        </div>
                    </div>

                    <div class="form-actions right">
                        <button type="button" class="btn default" onclick="history.go(-1)"><i class="fa fa-rotate-left"></i> 取消</button>
                        <button type="submit" class="btn green"><i class="fa fa-check"></i> 保存</button>
                    </div>
                </form>
                <!-- END FORM-->
            </div>
        </div>
    </div>
</div>
<@commonMacro.commonScript />
<script>
    (function ($) {
        $("#councilinfoForm").ajaxLoadFormData("/services/mars/councilinfo/getTotal?councilId=" + getParameter("councilId"));
    })(jQuery);

    function init() {
        $("#userInfoTable").pagingGrid(
                {
                    dataUrl: '/services/mars/userrelation/listCouncil?councilId='+getParameter("councilId")
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [],
                    pagingtoolbar: {
                        displayInfo: true
                    }
                }
        );
    }
    init();
</script>
</body>
</html>


