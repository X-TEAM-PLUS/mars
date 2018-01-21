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
            <i class="fa fa-cube"></i>
            <a href="#">提现报表</a>
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
                        <i class="icon-user"></i>提现报表
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
                <div class="portlet-body" id="withdrawRecordTable">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-12 pull-right">
                                <form id="withdrawRecordTableForm" onsubmit="init();return false;">
                                    <div class="input-group">
                                        <div class="input-icon col-md-2">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="userId" placeholder="用户ID"/>
                                        </div>
                                        <div class="input-icon col-md-2">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="bankAccountName" placeholder="提款人姓名"/>
                                        </div>
                                        <div class="input-icon col-md-2">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="bankAccountNo" placeholder="打款账号"/>
                                        </div>
                                        <div class="input-icon col-md-2">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="mobileNo" placeholder="提现人电话"/>
                                        </div>
                                        <div class="input-icon date-picker input-daterange col-md-2 " data-date-format="yyyy-mm-dd">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="payTime" class="form-control " readonly="" validate='{required: false}' placeholder="打款时间">
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
                            <th column="userId">用户ID</th>
                            <th column="bankAccountName">提款人姓名</th>
                            <th column="mobileNo">提现人电话</th>
                            <th column="applyTime">申请时间</th>
                            <th column="payTime">打款时间</th>
                            <th column="amount">提取金额（元）</th>
                            <th column="bankAccountNo">打款账号</th>
                            <th column="status" type="enum" enum-v="{'-1':'<span class=\'bold badge badge-danger\'>审批未通过</span>',0:'<span class=\'bold badge badge-default\'>未审批</span>',1:'<span class=\'badge badge-success \'>审批通过</span>',2:'<span class=\' bold badge badge-info\'>已打款</span>',3:'<span class=\'bold badge badge-primary \'>已确认</span>'}">状态</th>
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
        $("#withdrawRecordTable").pagingGrid(
                {
                    dataUrl: '/services/mars/withdrawrecord/list?status=2'
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [{
                    name: '同意提现申请'
                    ,buttonColor:'green'
                    , iconClass: 'glyphicon glyphicon-ok'
                    , action: '/services/mars/withdrawrecord/agreeApply'
                    , confirm: true
                    , ajax: true
                    , parmaName: 'id'
                    , column: 'id'
                    , equalField: 'status'
                    , equalValue: '0'
                },{
                    name: '不同意提现申请'
                    ,buttonColor:'red'
                    , iconClass: 'glyphicon glyphicon-remove'
                    , action: '/services/mars/withdrawrecord/refuseApply'
                    , confirm: true
                    , ajax: true
                    , parmaName: 'id'
                    , column: 'id'
                    , equalField: 'status'
                    , equalValue: '0'
                }
//                ,{
//                     name: '确认转帐'
//                    ,buttonColor:'blue'
//                    , iconClass: 'glyphicon glyphicon-earphone'
//                    , action: '/mars/withdrawrecord/edit'
//                    , confirm: true
//                    , ajax: true
//                    , parmaName: 'id'
//                    , column: 'id'
//                    , equalField: 'status'
//                    , equalValue: '1'
//        }
//                    , {
//                        name: '删除'
//                        , iconClass: 'glyphicon glyphicon-trash'
//                        , action: '/services/mars/withdrawrecord/delete'
//                        , ajax: true
//                        , confirm: true
//                        , parmaName: 'id'
//                        , column: 'id'
//                    }
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


