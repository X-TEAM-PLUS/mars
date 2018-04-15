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
            <a href="#">平台佣金明细表(用于记录每销售一张平台提成的明细)管理</a>
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
                        <i class="icon-user"></i>平台佣金明细表(用于记录每销售一张平台提成的明细)管理
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
                <div class="portlet-body" id="commissionDetailTable">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="btn-group">
                                    <button id="newCommissionDetailButton" class="btn green" onclick="location.href = 'add';">
                                        添加平台佣金明细表(用于记录每销售一张平台提成的明细) <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                            <div class="col-md-2 pull-right">
                                <form id="commissionDetailTableForm" onsubmit="init();return false;">
                                    <div class="input-group">
                                        <div class="input-icon">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="id" placeholder="流水号"/>
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
                            <th column="id">流水号</th>
                            <th column="ordreNo">订单号</th>
                            <th column="cardNo">健康卡号</th>
                            <th column="commissionType">佣金类型</th>
                            <th column="commissionAmount">佣金金额</th>
                            <th column="status">状态</th>
                            <th column="created">创建时间</th>
                            <th column="updated">更新时间</th>
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
        $("#commissionDetailTable").pagingGrid(
                {
                    dataUrl: '/services/mars/commissiondetail/list'
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [{
                        name: '修改'
                        , iconClass: 'fa fa-edit'
                        , action: '/mars/commissiondetail/edit'
                        , confirm: false
                        , parmaName: 'id'
                        , column: 'id'
                    }
                        , {
                            name: '删除'
                            , iconClass: 'glyphicon glyphicon-trash'
                            , action: '/services/mars/commissiondetail/delete'
                            , ajax: true
                            , confirm: true
                            , parmaName: 'id'
                            , column: 'id'
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


