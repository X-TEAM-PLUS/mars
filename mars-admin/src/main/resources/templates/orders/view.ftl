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
            <a href="index">订单表管理</a>
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
                    <i class="fa fa-adn"></i>查看订单详情
                </div>
                <div class="tools">
                    <a href="javascript:;" class="collapse">
                    </a>
                    <a href="#portlet-config" data-toggle="modal" class="config">
                    </a>
                    <a href="javascript:;" class="reload" onclick="init()">
                    </a>
                    <a href="javascript:;" class="remove" data-original-title="" title="" onclick="history.go(-1)">
                    </a>
                </div>
            </div>
            <div class="portlet-body form">
                <!-- BEGIN FORM-->
                <form class="form-horizontal" id="ordersForm">
                    <div class="form-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        订单号<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa   fa-font "></i>
                                            <input type="text" name="orderNo" class="form-control" placeholder="订单号" readonly disabled  validate='{required: true}'
                                            >
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        订单总价
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa  fa-rmb "></i>
                                            <input type="text" name="orderPrice" class="form-control" placeholder="订单总价" readonly disabled  validate='{required: false}'
                                                   maxlength="11">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        产品名称<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa  fa-user "></i>
                                            <input type="text" name="productName" class="form-control" placeholder="产品名称" readonly disabled  validate='{required: true}'
                                                   maxlength="50">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        产品单价<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa  fa-rmb "></i>
                                            <input type="text" name="productPrice" class="form-control" placeholder="产品单价"  readonly disabled  validate='{required: true}'
                                                   maxlength="11">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        卡类型<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <select id="cardType" name="cardType" class="form-control selectpicker" data-live-search="false"  readonly disabled  placeholder="卡类型" validate='{required: true,digits: true}'>
                                                <option value="0"  >虚拟卡</option>
                                                <option value="1">实体卡</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        数量<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa   fa-font "></i>
                                            <input type="text" name="productNum" class="form-control" placeholder="数量"  readonly disabled  validate='{required: true,digits: true}'
                                            >
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        卡号
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa   fa-font "></i>
                                            <input type="text" name="cardNo" class="form-control" placeholder="卡号" readonly disabled  validate='{required: false}'
                                            >
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        产品ID<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa   fa-font "></i>
                                            <input type="text" name="productId" class="form-control" placeholder="产品ID" readonly disabled  validate='{required: true}'
                                            >
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        购卡用户ID<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa  fa-user-circle "></i>
                                            <input type="text" name="buyerUserId" class="form-control" placeholder="购卡用户ID" readonly disabled  validate='{required: true}'
                                            >
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                <label class="col-md-3 control-label">
                                    售卡用户ID
                                </label>
                                <div class="col-md-5">
                                    <div class="input-icon margin-top-10">
                                        <i class="fa  fa-user-circle "></i>
                                        <input type="text" name="sellerUserId" class="form-control" placeholder="售卡用户ID" readonly disabled  validate='{required: false}'
                                        >
                                    </div>
                                </div>
                            </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        订单类型<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <select id="orderType" name="orderType" class="form-control selectpicker" data-live-search="false"  readonly disabled  placeholder="订单类型" validate='{required: true,digits: true}'>
                                                <option value="0"  >平台直销</option>
                                                <option value="1">会员分销</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        状态<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <select id="status" name="status" class="form-control selectpicker" data-live-search="false"  readonly disabled  placeholder="状态" validate='{required: true,digits: true}'>
                                                <option value="-1"  >已失效</option>
                                                <option value="0"  >未支付</option>
                                                <option value="1">已支付</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        下单时间<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="orderTime" class="form-control" readonly="" readonly disabled   validate='{required: true}'>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        支付时间
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="payTime" class="form-control" readonly disabled  validate='{required: false}'>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        支付方式<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <select id="payWay" name="payWay" class="form-control selectpicker" data-live-search="false"  readonly disabled  placeholder="支付方式" validate='{required: true,digits: true}'>
                                                <option value="1"  >微信支付</option>
                                                <option value="2">支付宝</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        支付流水号
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa   fa-font "></i>
                                            <input type="text" name="payOrderNo" class="form-control" placeholder="支付流水号" readonly disabled   validate='{required: false}'
                                                   maxlength="128">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        创建时间<span class="required">* </span>
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="created" class="form-control" readonly="" validate='{required: true}'>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        更新时间
                                    </label>
                                    <div class="col-md-5">
                                        <div class="input-icon margin-top-10">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="updated" class="form-control" readonly="" validate='{required: false}'>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions right">
                        <button type="button" class="btn default" onclick="history.go(-1)"><i class="fa fa-rotate-left"></i> 取消</button>
                    </div>
                </form>
                <!-- END FORM-->
            </div>
        </div>
    </div>
</div>
<@commonMacro.commonScript />
<script>
    function init() {
        $("#ordersForm").ajaxLoadFormData("/services/mars/orders/get?orderNo=" + getParameter("orderNo"));
    }
    //初始化
    init();
</script>
</body>
</html>


