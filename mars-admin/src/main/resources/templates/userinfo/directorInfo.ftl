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
            <a href="socialworker">社工基本信息</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li class=" active">
            <i class="fa fa-edit "></i>
            <a href="#">详情</a>
        </li>
    </ul>
</div>
<div class="page-content">
    <div class="tab-pane active" id="tab_0">
        <div class="portlet box grey-cascade">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-adn"></i>社工基本信息
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
                <form action="/services/mars/userinfo/put" class="form-horizontal" id="userinfoForm">
                    <input type="hidden" name="userId" id="userId">

                    <div class="form-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        用户主键
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="userId" class="form-control"
                                               id="userId"
                                               placeholder="用户主键" style="width: 100%" readonly="true">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        用户姓名
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="nickName" class="form-control"
                                               id="nickName"
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
                                        手机号
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="mobileNo" class="form-control"
                                               id="mobileNo"
                                               placeholder="手机号" style="width: 100%" readonly="true">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        联系地址
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="linkAddress" class="form-control"
                                               id="linkAddress"
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
                                        身份证
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="idNumber" class="form-control"
                                               id="idNumber"
                                               placeholder="身份证" style="width: 100%" readonly="true">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        注册来源/推荐人
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="registerSource" class="form-control"
                                               id="registerSource"
                                               placeholder="注册来源" style="width: 100%"
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
                                        注册时间
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="registerTime" class="form-control"
                                               id="registerTime" style="width: 100%" readonly="true">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        最后登陆时间
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="lastLoginTime" class="form-control"
                                               id="lastLoginTime" style="width: 100%" readonly="true">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        登陆次数
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="loginCount" class="form-control"
                                               id="loginCount" style="width: 100%" readonly="true">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        社工申请时间
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="applyInfo.created" class="form-control"
                                               id="applyInfo.created" style="width: 100%" readonly="true">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        团队人数
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="userSalesInfo.teamPeopleNumber"
                                               class="form-control"
                                               id="userSalesInfo.teamPeopleNumber" style="width: 100%"
                                               readonly="true">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        推广人数
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="userSalesInfo.promotionNumber" class="form-control"
                                               id="userSalesInfo.promotionNumber" style="width: 100%"
                                               readonly="true">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        团队补贴收益
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="accumulativeSubsidy" class="form-control"
                                               id="accumulativeSubsidy" style="width: 100%"
                                               readonly="true">
                                    </div>
                                </div>
                            </div>
                            </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">
                                        申请原因
                                    </label>
                                    <div class="col-md-10 container-fluid">
                                            <textarea class="form-control" id="applyInfo.applyReason"
                                                      name="applyInfo.applyReason" rows="3"
                                                      style="height:expression((this.scrollHeight>150)?'150px':(this.scrollHeight+5)+'px');overflow:auto;"
                                                      maxlength="25"></textarea>
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
                                <th column="applyInfo.created">申请日期</th>
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
                        <button type="button" class="btn default" onclick="history.go(-1)"><i
                                class="fa fa-rotate-left"></i> 取消
                        </button>
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
        $("#userinfoForm").ajaxLoadFormData("/services/mars/userinfo/getWorker?userId=" + getParameter("userId"));
    })(jQuery);
    function init() {
        $("#userInfoTable").pagingGrid(
                {
                    dataUrl: '/services/mars/userrelation/list?refereeUserId='+getParameter("userId")
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


