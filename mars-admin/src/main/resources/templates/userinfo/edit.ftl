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
            <a href="index">用户基本信息表管理</a>
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
                    <i class="fa fa-adn"></i>编辑用户基本信息表
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
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                会员卡号
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="cardNo" class="form-control" placeholder="会员卡号" validate='{required: false}'
                                           maxlength="9">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                身份证号
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="idNumber" class="form-control" placeholder="身份证号" validate='{required: false}'
                                           maxlength="18">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                会员开通方式
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="cardActivateMode" class="form-control" placeholder="会员开通方式" validate='{required: false,digits: true}'
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                真实姓名
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa  fa-user "></i>
                                    <input type="text" name="realName" class="form-control" placeholder="真实姓名" validate='{required: false}'
                                           maxlength="20">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                会员卡类型
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="cardType" class="form-control" placeholder="会员卡类型" validate='{required: false}'
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                联系地址
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="linkAddress" class="form-control" placeholder="联系地址" validate='{required: false}'
                                           maxlength="128">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                会员卡有效期
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="cardLifeTime" class="form-control" readonly="" validate='{required: false}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                手机号
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa  fa-phone-square "></i>
                                    <input type="text" name="mobileNo" class="form-control" placeholder="手机号" validate='{required: false}'
                                           maxlength="11">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                会员卡激活日期
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="cardActivateTime" class="form-control" readonly="" validate='{required: false}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                注册时间<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="registerTime" class="form-control" readonly="" validate='{required: true}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                创建时间<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="created" class="form-control" readonly="" validate='{required: true}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                微信账户
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa  fa-user-circle "></i>
                                    <input type="text" name="weixinAccount" class="form-control" placeholder="微信账户" validate='{required: false}'
                                           maxlength="128">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                状态<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="status" class="form-control" placeholder="状态" validate='{required: true,digits: true}'
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                登录次数
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="loginCount" class="form-control" placeholder="登录次数" validate='{required: false}'
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                注册来源
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="registerSource" class="form-control" placeholder="注册来源" validate='{required: false}'
                                           maxlength="128">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                更新时间
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="updated" class="form-control" readonly="" validate='{required: false}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                密码
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="password" class="form-control" placeholder="密码" validate='{required: false}'
                                           maxlength="20">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                用户级别<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa  fa-user-circle "></i>
                                    <input type="text" name="userLevel" class="form-control" placeholder="用户级别" validate='{required: true,digits: true}'
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                用户ID<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa  fa-user-circle "></i>
                                    <input type="text" name="userId" class="form-control" placeholder="用户ID" validate='{required: true}'
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                最后登录时间
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="lastLoginTime" class="form-control" readonly="" validate='{required: false}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                昵称
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa  fa-user "></i>
                                    <input type="text" name="nickName" class="form-control" placeholder="昵称" validate='{required: false}'
                                           maxlength="20">
                                </div>
                            </div>
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
        $("#userinfoForm").ajaxLoadFormData("/services/mars/userinfo/get?userId=" + getParameter("userId"));
    })(jQuery);
</script>
</body>
</html>


