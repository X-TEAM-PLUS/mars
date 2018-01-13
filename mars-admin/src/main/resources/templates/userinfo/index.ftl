<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<#import '/common/common.ftl' as commonMacro>
<@commonMacro.commonStyle />
</head>
<body class="page-content">
<!-- BEGIN PAGE HEADER-->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a href="/welcome">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a href="#">用户基本信息表管理</a>
            <i class="fa fa-cube"></i>
        </li>

    </ul>
</div>
<div class="page-content">
    <!-- END PAGE HEADER-->
    <!-- BEGIN PAGE CONTENT-->
    <div class="row">
        <div class="col-md-12">
            <!-- BEGIN EXAMPLE TABLE PORTLET-->
            <div class="portlet box grey-cascade">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-user"></i>用户基本信息表管理
                    </div>
                    <div class="tools">
                        <a href="javascript:;" class="collapse">
                        </a>
                        <a href="#portlet-config" data-toggle="modal" class="config">
                        </a>
                        <a href="javascript:;" class="reload" onclick="init()">
                        </a>
                    </div>
                </div>
                <div class="portlet-body" id="userInfoTable">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="btn-group">
                                    <button id="newUserInfoButton" class="btn green" onclick="location.href = 'add';">
                                        添加用户基本信息表 <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                            <div class="col-md-2 pull-right">
                                <form id="userInfoTableForm" onsubmit="init();return false;">
                                    <div class="input-group">
                                        <div class="input-icon">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="userId" placeholder="用户ID"/>
                                        </div>
                                        <span class="input-group-btn">
												<button class="btn btn-success" type="submit"><i class="fa fa-arrow-left fa-fw"/></i> 搜索</button>
												</span>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover ">
                        <thead>
                        <tr>
                            <th column="userId" type="checkbox" style="width: 50px"><input type="checkbox" id="allCheckBox" class="allCheckBox"></th>
                            <th column="cardNo">会员卡号</th>
                            <th column="idNumber">身份证号</th>
                            <th column="cardActivateMode">会员开通方式</th>
                            <th column="realName">真实姓名</th>
                            <th column="cardType">会员卡类型</th>
                            <th column="linkAddress">联系地址</th>
                            <th column="cardLifeTime">会员卡有效期</th>
                            <th column="mobileNo">手机号</th>
                            <th column="cardActivateTime">会员卡激活日期</th>
                            <th column="registerTime">注册时间</th>
                            <th column="created">创建时间</th>
                            <th column="weixinAccount">微信账户</th>
                            <th column="status">状态</th>
                            <th column="loginCount">登录次数</th>
                            <th column="registerSource">注册来源</th>
                            <th column="updated">更新时间</th>
                            <th column="passwork">密码</th>
                            <th column="userLevel">用户级别</th>
                            <th column="userId">用户ID</th>
                            <th column="lastLoginTime">最后登录时间</th>
                            <th column="nickName">昵称</th>
                            <th type="action" style="width: 200px">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div class="table-toolbar pagination-toolbar">
                    </div>
                </div>
            </div>
            <!-- END EXAMPLE TABLE PORTLET-->
        </div>
    </div>
</div>
<@commonMacro.commonScript />
<script>
    function init() {
        $("#userInfoTable").pagingGrid(
                {
                    dataUrl: '/services/mars/userinfo/list'
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [{
                        name: '修改'
                        , iconClass: 'fa fa-edit'
                        , action: '/mars/userinfo/edit'
                        , confirm: false
                        , parmaName: 'userId'
                        , column: 'userId'
                    }
                        , {
                            name: '删除'
                            , iconClass: 'glyphicon glyphicon-trash'
                            , action: '/services/mars/userinfo/delete'
                            , ajax: true
                            , confirm: true
                            , parmaName: 'userId'
                            , column: 'userId'
                        }
                    ],
                    pagingtoolbar: {
                        displayInfo: true
                    }
                }
        );
    }

    //初始化
    init();

</script>


</body>
</html>


