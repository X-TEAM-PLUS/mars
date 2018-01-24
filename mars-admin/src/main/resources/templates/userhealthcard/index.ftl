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
            <a href="#">用户健康卡信息表管理</a>
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
                        <i class="icon-user"></i>用户健康卡信息表管理
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
                <div class="portlet-body" id="userHealthCardTable">
                    <div class="table-toolbar">
                        <form id="userHealthCardTableForm" onsubmit="init();return false;">
                        <div class="row">
                            <div class="col-md-3 pull-right">
                                    <div class="input-group">
                                        <div class="input-icon">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="cardNo" placeholder="卡号"/>
                                        </div>
                                        <span class="input-group-btn">
												<button class="btn btn-success" type="submit"><i class="fa fa-arrow-left fa-fw"/></i> 搜索</button>
												</span>
                                    </div>

                            </div>
                            <div class="col-md-3 pull-right">
                                <div class="input-group">
                                    <div class="input-icon">
                                        <i class="icon-magnifier"></i>
                                        <select class="form-control" id="status" name="status"
                                                style="width: 100%">
                                            <option value="">请选择状态</option>
                                            <option value="0">未激活</option>
                                            <option value="1">已激活</option>
                                            <option value="2">已过期</option>
                                            <option value="3">已续费</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 pull-right">
                                <div class="input-group">
                                    <div class="input-icon">
                                        <i class="icon-magnifier"></i>
                                        <input class="form-control" type="text" name="activateUserInfo.realName" placeholder="用户姓名"/>
                                    </div>
                                </div>

                            </div>
                        </div>

                        </form>
                    </div>
                    <table class="table table-striped table-bordered table-hover ">
                        <thead>
                        <tr>
                            <th column="sendTotalCount">总次数</th>
                            <th column="sendCount">已发货次数</th>

                            <th column="status" type="enum" enum-v="{'0':'未激活','1':'已激活','2':'使用中','3':'已完成'}">状态</th>
                            <th column="survivalPeriodMode">有效期类型</th>
                            <th column="created">创建时间</th>
                            <th column="survivalPeriodNum">有效期期数</th>
                            <th column="updated">更新时间</th>
                            <th column="cardNo">卡号</th>
                            <th column="cardActivateTime">会员卡激活日期</th>
                            <th column="activateUserInfo.realName">用户姓名</th>
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
        $("#userHealthCardTable").pagingGrid(
                {
                    dataUrl: '/services/mars/userhealthcard/listActiveUser'
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [{
                        name: '修改'
                        , iconClass: 'fa fa-list-ul'
                        , action: '/mars/userhealthcard/activeUser'
                        , confirm: false
                        , parmaName: 'cardNo'
                        , column: 'cardNo'
                    }],
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


