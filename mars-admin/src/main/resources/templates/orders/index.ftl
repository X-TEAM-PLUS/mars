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
            <a href="#">订单表管理</a>
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
                        <i class="icon-user"></i>订单表管理
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
                <div class="portlet-body" id="ordersTable">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-12 pull-right">
                                <form id="ordersTableForm" onsubmit="init();return false;">
                                    <div class="input-group">
                                        <div class="input-icon col-md-2">
                                            <select id="cardType" name="status" class="form-control selectpicker" data-live-search="false" placeholder="订单状态" >
                                                <option value="" >订单状态---全部</option>
                                                <option value="0"  >订单状态---已失效</option>
                                                <option value="0"  >订单状态---未支付</option>
                                                <option value="1">订单状态---已支付</option>
                                            </select>
                                        </div>
                                        <div class="input-icon  col-md-2">
                                            <select id="cardType" name="cardType" class="form-control selectpicker" data-live-search="false" placeholder="卡类型" >
                                                <option value="" >卡类型---全部</option>
                                                <option value="0"  >卡类型---虚拟卡</option>
                                                <option value="1">卡类型---实体卡</option>
                                            </select>
                                        </div>
                                        <div class="input-icon col-md-2">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="buyerUserId" placeholder="购卡用户ID"/>
                                        </div>
                                        <div class="input-icon col-md-2">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="mobileNo" placeholder="手机号"/>
                                        </div>
                                        <div class="input-icon col-md-2">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="orderNo" placeholder="订单号"/>
                                        </div>
                                        <div class="input-icon date-picker input-daterange col-md-2 " data-date-format="yyyy-mm-dd">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="orderTime" class="form-control " readonly="" placeholder="下单时间">
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
                            <th column="orderNo">订单号</th>
                            <th column="orderTime">下单时间</th>
                            <th column="buyerUserId">用户ID</th>
                            <th column="realName">姓名</th>
                            <th column="productName">产品名称</th>
                            <th column="cardType"  type="enum"  enum-v="{0:'虚拟卡',1:'实体卡'}">卡类型</th>
                            <th column="productPrice">产品单价(元)</th>
                            <th column="productNum">数量</th>
                            <th column="orderPrice">订单总价(元)</th>
                            <th column="status"  type="enum"  enum-v="{'-1':'<span class=\'bold badge badge-default\'>已失效</span>',0:'<span class=\'bold badge badge-info\'>未支付</span>',1:'<span class=\'bold badge badge-success\'>已支付</span>'}">状态</th>
                            <th column="payTime">支付时间</th>
                            <th column="payWay"  type="enum"  enum-v="{1:'微信支付',2:'支付宝'}">支付方式</th>
                            <th column="payOrderNo">支付流水号</th>
                            <th column="updated">更新时间</th>
                            <th type="action" >操作</th>
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
        $("#ordersTable").pagingGrid(
                {
                    dataUrl: '/services/mars/orders/list'
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [{
                        name: '修改'
                        , iconClass: 'fa fa-edit'
                        , action: '/mars/orders/edit'
                        , confirm: false
                        , parmaName: 'orderNo'
                        , column: 'orderNo'
                    }
                        , {
                            name: '删除'
                            , iconClass: 'glyphicon glyphicon-trash'
                            , action: '/services/mars/orders/delete'
                            , ajax: true
                            , confirm: true
                            , parmaName: 'orderNo'
                            , column: 'orderNo'
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


